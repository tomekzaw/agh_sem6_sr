import typing
from time import time
from datetime import datetime

def fix_stop_name(stop_name: str) -> str:
    return {
        'BabaJaga': 'Baba Jaga',
        'Batalionu \'Skała\' AK': 'Batalionu "Skała" AK',
        'Cm. Rakowicki': 'Cmentarz Rakowicki',
        'Dworzec Głowny Zachód': 'Dworzec Główny Zachód',
        'Grabie Kościół (nż)': 'Grabie Kościół',
        'Jeziorzany Petla': 'Jeziorzany Pętla',
        'Lusina Petla': 'Lusina Pętla',
        'Pl. Boh. Getta': 'Plac Bohaterów Getta',
        'Rybitwy Rozjazd (nż)': 'Rybitwy Rozjazd',
        'Skawinia Podbory': 'Skawina Podbory',
        'Tauron Arena Kraków Wieczysta': 'TAURON Arena Kraków Wieczysta',
        'Szpital Uniwersytecki / Instytut Pediatr': 'Szpital Uniwersytecki / Instytut Pediatrii',
        'WARSZTAT': 'Warsztat',
        'Wzgórza K.': 'Wzgórza Krzesławickie',
    }.get(stop_name, stop_name)

def current_timestamp() -> int:
    return int(time() * 1000)

def time_str_to_int(time_str: str) -> int:
    hh, mm = time_str.split(':', 1)
    return 60 * int(hh) + int(mm)

def time_int_to_str(time_int: int) -> str:
    return f'{time_int//60:02d}:{time_int%60:02d}'

def calculate_delay(planned_time_int: int, actual_time_int: int) -> int:
    delay = actual_time_int - planned_time_int
    if delay < -(12 * 60):
        return delay % (24 * 60)
    return delay
