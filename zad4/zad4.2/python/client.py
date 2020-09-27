# -*- coding: utf-8 -*-
import sys
from termcolor import colored
from datetime import datetime

import Ice
Ice.loadSlice('../slice/smarthome.ice')
import SmartHome

def log(message, color=None):
    print(colored(f'[{datetime.now()}] {message}', color))

admin = False

if __name__ == '__main__':
    with Ice.initialize(['--Ice.Config=config.client'] + sys.argv) as communicator:

        while True:
            try:
                line = input(f'client{"#" if admin else ">"} ')
            except KeyboardInterrupt:
                break

            if line == '':
                continue

            if line in ('en', 'enable'):
                admin = True
                continue

            if line == 'exit':
                if admin:
                    admin = False
                else:
                    break
                continue

            if not admin:
                log('Access forbidden', 'red')
                continue

            try:
                args = line.lower().split(' ')

                if args[0] in ('temp1', 'temp2'):
                    num = args[0][-1]
                    base = communicator.propertyToProxy(f'TempSensor{num}.Proxy')
                    tempsensor = SmartHome.TempSensorPrx.checkedCast(base)
                    if not tempsensor:
                        raise RuntimeError('Invalid proxy')

                    temp = tempsensor.getTemperature()
                    log(f'Temperature: {temp:.1f} °C', 'blue')
                    continue

                if args[0] in ('cam1', 'camera1'):
                    num = args[0][-1]
                    base = communicator.propertyToProxy(f'Camera1.Proxy')
                    camera = SmartHome.CameraPrx.checkedCast(base)
                    if not camera:
                        raise RuntimeError('Invalid proxy')

                    if args[1] in ('take', 'takepic', 'takepicture'):
                        try:
                            camera.takePicture()
                            log('Photo taken', 'green')
                        except SmartHome.NoMemorySpaceError:
                            log('No memory space', 'red')
                        continue

                    if args[1] in ('is', 'isrec', 'isrecording'):
                        log(camera.isRecording(), 'blue')
                        continue

                    if args[1] == 'getmode':
                        log(camera.getMode(), 'blue')
                        continue

                    if args[1] == 'setmode':
                        if args[2] in ('p', 'prog', 'progressive'):
                            mode = SmartHome.Mode.Progressive
                        elif args[2] in ('i', 'inter', 'interlaced'):
                            mode = SmartHome.Mode.Interlaced
                        else:
                            raise ValueError('Invalid mode')
                        try:
                            camera.setMode(mode)
                            log('Mode changed to ' + str(mode).lower(), 'blue')
                        except SmartHome.RecordingAlreadyStartedError:
                            log('Recording already started', 'red')
                        continue

                    if args[1] in ('start', 'startrecording'):
                        try:
                            camera.startRecording()
                            log('Started recording', 'green')
                        except SmartHome.RecordingAlreadyStartedError:
                            log('Recording already started', 'yellow')
                        continue

                    if args[1] in ('stop', 'stoprecording'):
                        try:
                            camera.stopRecording()
                            log('Stopped recording', 'green')
                        except SmartHome.RecordingNotStartedError:
                            log('Recording not started', 'yellow')
                        continue

                if args[0] in ('cam2', 'camera2'):
                    num = args[0][-1]
                    base = communicator.propertyToProxy(f'PTZCamera2.Proxy')
                    camera = SmartHome.PTZCameraPrx.checkedCast(base)
                    if not camera:
                        raise RuntimeError('Invalid proxy')

                    if args[1] in ('take', 'takepic', 'takepicture'):
                        try:
                            camera.takePicture()
                            log('Photo taken', 'green')
                        except SmartHome.NoMemorySpaceError as exc:
                            log('No memory space', 'red')
                        continue

                    if args[1] in ('is', 'isrec', 'isrecording'):
                        log(camera.isRecording(), 'blue')
                        continue

                    if args[1] == 'getmode':
                        log(camera.getMode(), 'blue')
                        continue

                    if args[1] == 'setmode':
                        if args[2] in ('p', 'prog', 'progressive'):
                            mode = SmartHome.Mode.Progressive
                        elif args[2] in ('i', 'inter', 'interlaced'):
                            mode = SmartHome.Mode.Interlaced
                        else:
                            raise ValueError('Invalid mode')
                        try:
                            camera.setMode(mode)
                            log('Mode changed to ' + str(mode).lower(), 'blue')
                        except SmartHome.RecordingAlreadyStartedError:
                            log('Recording already started', 'red')
                        continue

                    if args[1] in ('start', 'startrecording'):
                        try:
                            camera.startRecording()
                            log('Started recording', 'green')
                        except SmartHome.RecordingAlreadyStartedError:
                            log('Recording already started', 'yellow')
                        continue

                    if args[1] in ('stop', 'stoprecording'):
                        try:
                            camera.stopRecording()
                            log('Stopped recording', 'green')
                        except SmartHome.RecordingNotStartedError:
                            log('Recording has not been started', 'yellow')
                        continue

                    if args[1] in ('getptz', 'getpantiltzoom'):
                        ptz = camera.getPanTiltZoom()
                        log(f'pan={ptz.pan} tilt={ptz.tilt} zoom={ptz.zoom}', 'green')
                        continue

                    if args[1] in ('setptz', 'setpantiltzoom'):
                        pan, tilt, zoom = map(int, args[2:][:3])
                        ptz = SmartHome.PanTiltZoom(pan, tilt, zoom)
                        try:
                            camera.setPanTiltZoom(ptz)
                            log(f'PTZ set', 'green')
                        except SmartHome.InvalidPanTiltZoomError:
                            log('Invalid pan-tilt-zoom', 'red')
                        continue

                if args[0] == 'bul1':
                    base = communicator.propertyToProxy('Bulbulator1.Proxy')
                    bulbulator = SmartHome.BulbulatorPrx.checkedCast(base)
                    if not bulbulator:
                        raise RuntimeError('Invalid proxy')

                    bulbulator.bulbul()
                    log('Bulbuling completed', 'green')
                    continue

                print(colored('Invalid command', 'red'))

            except (IndexError, ValueError):
                print(colored('Invalid syntax', 'red'))
            except Exception as e:
                print(colored('Something went wrong', 'red'))
