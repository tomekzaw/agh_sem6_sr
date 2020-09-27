import requests
from flask import Flask, request, render_template
from itertools import chain
from datetime import datetime, timedelta  
from collections import defaultdict
from statistics import mean
from utils import *

app = Flask(__name__, static_folder='', template_folder='')

@app.route('/', methods=['GET'])
def root():
    return app.send_static_file('form.html')

@app.route('/api/v1/delays', methods=['GET'])
def delays():

    # retrieve request params
    arg_line = request.args.get('line')
    if not arg_line:
        return render_template('error.html', message='Nie wpisano numeru linii')

    arg_direction = request.args.get('direction')
    if not arg_direction:
        return render_template('error.html', message='Nie wpisano kierunku')

    # choose API depending on line number
    if len(arg_line) == 3:
        ttss_base_url = 'http://ttss.mpk.krakow.pl'  # buses
    else:
        ttss_base_url = 'http://www.ttss.krakow.pl'  # trams

    # get all routes
    response = requests.get(ttss_base_url + '/internetservice/services/routeInfo/route', {'language': 'pl'})
    routes = response.json()['routes']
    
    # find route for specified line number
    try:
        route = next(filter(lambda route: route['name'] == arg_line, routes))
    except StopIteration:
        return render_template('error.html', message=f'Nie znaleziono linii {arg_line}')

    # get route id
    route_id = int(route['id'])

    # get alternative directions
    all_directions = route['directions']
    alternative_directions = set(all_directions)
    if arg_direction in all_directions:
        alternative_directions.remove(arg_direction)

    # get all stops of found route
    timestamp = current_timestamp()
    response = requests.get(ttss_base_url + '/internetservice/services/routeInfo/routeStops', {
        'routeId': route_id,
        'language': 'pl',
        'cacheBuster': timestamp,
    })
    route_stops = response.json()['stops']

    # retrieve trip ids and delays for each stop
    trip_ids = set()
    delays = defaultdict(dict)
    now = datetime.now()
    now_int = 60 * (60 * now.hour + now.minute) + now.second

    for route_stop in route_stops:
        stop_num = int(route_stop['number'])
        stop_name = route_stop['name']

        response = requests.get(ttss_base_url + '/internetservice/services/passageInfo/stopPassages/stop', {
            'language': 'pl',
            'stop': stop_num,
            'mode': 'departure',
            'startTime': timestamp,
            'timeFrame': 4*60,  # in minutes
            'cacheBuster': timestamp,
        })
        departures = response.json()

        # get departures of specified line only
        line_departures = (
            departure
            for departure in chain(departures['old'], departures['actual'])
            if departure['patternText'] == arg_line and fix_stop_name(departure['direction']) == arg_direction
        )

        # process each departure from this stop
        for departure in line_departures:
            trip_id = int(departure['tripId'])
            trip_ids.add(trip_id)

            # retrieve information about planned and actual departure times and calculate the delay
            planned_time_str = departure['plannedTime']
            planned_time_int = time_str_to_int(planned_time_str)

            if departure['status'] == 'PLANNED':                
                actual_time_str = planned_time_str
                delay = None  # vehicle is not logged to system
            else:
                actual_time_int = round(int(now_int + int(departure['actualRelativeTime'])) / 60) % (24 * 60)
                actual_time_str = time_int_to_str(actual_time_int)
                delay = calculate_delay(planned_time_int, actual_time_int)

            delays[trip_id][stop_num] = (actual_time_str, delay)

    # display error page if no trips have been found
    if not trip_ids:
        message = f'Nie znaleziono pojazdów obsługujących aktualnie linię {arg_line} w kierunku {arg_direction}'
        return render_template('error.html', message=message)

    stop_seq_nums_to_nums = {}
    stop_nums_to_names = {}

    # get all trips of specified line to retrieve sequence number of each stop
    for trip_id in trip_ids:
        response = requests.get(ttss_base_url + '/internetservice/services/tripInfo/tripPassages', {
            'tripId': trip_id,
            'mode': 'departure',
            'language': 'pl',
            'cacheBuster': timestamp
        })
        trip = response.json()

        departures = chain(trip['old'], trip['actual'])
        for departure in departures:
            stop_seq_num = int(departure['stop_seq_num'])
            stop_num = int(departure['stop']['shortName'])
            stop_name = departure['stop']['name']

            if stop_seq_num not in stop_seq_nums_to_nums:
                stop_seq_nums_to_nums[stop_seq_num] = stop_num

            if stop_num not in stop_nums_to_names:
                stop_nums_to_names[stop_num] = stop_name

    # sort trips by arrival time at the last stop
    trip_ids = tuple(sorted(trip_ids, key=lambda trip_id: max(delays[trip_id].values())))  

    # sort stops by sequence number
    stops = tuple(sorted(
        (stop_seq_num, stop_num, fix_stop_name(stop_nums_to_names[stop_num]))
        for stop_seq_num, stop_num in stop_seq_nums_to_nums.items()
    ))

    # calculate min, max and average delays
    trip_delays = tuple(filter(None.__ne__, (
        max(delays_for_trip.values())[1]
        for delays_for_trip in delays.values()
    )))
    if trip_delays:
        min_trip_delay = min(trip_delays)
        max_trip_delay = max(trip_delays)
        avg_trip_delay = mean(trip_delays)
    else:
        min_trip_delay = max_trip_delay = avg_trip_delay = None

    # render html page
    return render_template('delays.html', line=arg_line, direction=arg_direction, trip_ids=trip_ids, delays=delays, stops=stops,
        min_trip_delay=min_trip_delay, max_trip_delay=max_trip_delay, avg_trip_delay=avg_trip_delay, alternative_directions=alternative_directions)

if __name__ == '__main__':
    app.run()
