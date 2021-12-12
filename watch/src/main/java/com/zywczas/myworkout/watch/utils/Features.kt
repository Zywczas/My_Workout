package com.zywczas.myworkout.watch.utils

/**
todo

SYNCHRONIZACJA:
kazdy element bedzie mial swoj timestamp ktory bedzie uaktualniany przy kazdej zmianie elementu
bede porownywac nazwe elementow i timestamp, z elementow o tej samej nazwie bedzie brany ten nowszy i zastapi wszystko w srodku
dwa rozne tygodnie nie moga miec tej samej nazwy
dwa rozne dni nie moga miec tej samej nazwy w jednym tygodniu
dwa rozne cwiczenia nie moga miec tej samej nazwy w jednym dniu
dwa rozne kardio nie moga miec tej samej nazwy w jednym dniu
w bibliotece kardio nie moze byc dwoch roznych o tych samych nazwach

EKRANY:
...
pokazywanie jak dlugo faza ekscentryczna i koncentryczna (2s)
pokazywanie aktualnej godziny podczas cwiczenia i minutnika

USUWANIE:
nie moze byc swipe to dismiss bo ekrany sa tak zamykane i bedzie sie klocic ze soba
usuwanie tygodni na dlugi klik
usuwanie dni na dlugi klik
usuwanie cwiczen na dlugi klik
usuwanie w ustawieniach jest na stale

KOLEJNOSC:
Sprobowac dac zmiane kolejnosci na drag and drop w fast adapterze
ustawianie kolejnosci dni treningowych
ustawianie kolejnosci tygodni treningowych
ustawianie kolejnosci cwiczen

LOGIKA:
-Jesli ktos ma 3 rozne tygodnie to pokazuje 3 wstecz i 3 do przodu, jesli ktos ma 1 tylko to pokazuje 1 wstecz i 1 aktualny(jezeli jest skonczony to zaczyna nowy)
-zaznaczanie zrobionego tygodnia - jezeli wszystkie dni sa zrobione
-zaznaczanie zrobionego dnia - jezeli wszystkie cwiczenia sa zrobione, pokazywanie daty "zrobione" jak sie wszystkie cwiczenia zrobi, a jak nie to bedzie
-zaznaczanie zrobionego cwiczenia - jak sie przechodzi do kolejnego cwiczenia, a przy ostatnim cwiczeniu przy ostatniej serii guzik "Zakoncz cwiczenie"
-klikniecie nowy tydzien/dzien/cwiczenie przenosi do wpisywania tekstu a potem do szczegolow danego elementu
-jak dodaje na koniec dnia jakies kardio to wczytac to co zapisane i pozwolic na zmiane przed zakonczeniem dodawania

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