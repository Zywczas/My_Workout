package com.zywczas.myworkout.watch.utils

/**
todo

SYNCHRONIZACJA:

podejscie 2: mam jednego timestampa dla calej bazy treningi i osobnego dla timera
patrze ktory timestamp jest nowszy i biore wszystko z niego

EKRANY:
...
pokazywanie jak dlugo faza ekscentryczna i koncentryczna (2s)
pokazywanie aktualnej godziny podczas cwiczenia i minutnika

USUWANIE:
usuwanie na guzik usun na koncu kazdej listy:
usuwanie tygodnia
usuwanie dnia
usuwanie cwiczenia
po kazdym usunieciu sprawdzic czy dany dzien lub tydzien jest skonczony, bo nagle skraca sie ilosc treningow, datetime dac taki jak moment usuniecia

KOLEJNOSC:
Sprobowac dac zmiane kolejnosci na drag and drop w fast adapterze
ustawianie kolejnosci dni treningowych
ustawianie kolejnosci tygodni treningowych
ustawianie kolejnosci cwiczen

LOGIKA:
- cardio - uproszczone: daje tylko info ze kardio zrobione, nic wiecej
- wyswietlam 5 ostatnie tygodnie, reszte usuwam
-zaznaczanie zrobionego cwiczenia - jak sie przechodzi do kolejnego cwiczenia, a przy ostatnim cwiczeniu przy ostatniej serii guzik "Zakoncz cwiczenie"

3 SCENARIUSZE:
Nowy uzytkownik ktory wszystko od nowa ustawia
Ktos kto juz odpala aplikacje w srodku tygodnia
Ktos kto chce pozmieniac to co juz ma w srodku tygodnia.

USTAWIENIA
czasu miedzy seriami
wibracja czy dzwonek
pikanie np. co dwie sekundy zeby kontrolowac tempo cwiczenia,

NA TELEFONIE:
wyszukiwanie juz istniejacych cwiczen

PLAN ROZWOJU:
Stworzyc apke na zegarek
Dac crashlytics
Nauczyc sie jetpack compose.
Poprawic jej wyglad pod to co mam w OpsenioHRM
Sworzyc apke na komorke z wykorzystaniem patentow z OpsenioHRM i jetpack compose.
Dac zapis do Firebase.
Dodac kwestie bezpieczenstwa - zapisywanie na komorce, shared prefs, blokady zgrywania ekranu.

 todo ustawic detekt tak zeby rowniez przy commitach skanowal kod
todo dac scrollowanie do pierwszego elementu ktory teraz trzeba wykonac, np. do kolejnego tygodnia, dnia lub treningu (przy otwieraniu aktywnosci)

 */