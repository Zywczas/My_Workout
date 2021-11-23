package com.zywczas.myworkout.watch.utils

/**
todo

popatrzec co one daja:
BoxInsetLayout. A layout that applies insets for round screens, so that developers can use the same layout and data for different Wear OS screen shapes.
SwipeDismissFrameLayout. A layout that enables a user to dismiss any view by swiping on the screen from left to right. Wear OS users expect left to right swiping for the back action.
WearableRecyclerView. A view that provides a curved layout, such as the layout used for the main Wear OS application launcher.
AmbientModeSupport. A class used with the AmbientModeSupport.AmbientCallbackProvider interface to provide support for ambient mode.

EKRANY:
...
pokazywanie jak dlugo faza ekscentryczna i koncentryczna (2s)
pokazywanie aktualnej godziny podczas cwiczenia i minutnika

USUWANIE:
usuwanie tygodni na dlugi klik albo swipe to dismiss
usuwanie dni na dlugi klik albo swipe to dismiss
usuwanie cwiczen na dlugi klik albo swipe to dismiss
usuwanie w ustawieniach jest na stale

KOLEJNOSC:
Sprobowac dac zmiane kolejnosci na drag and drop w fast adapterze
ustawianie kolejnosci dni treningowych
ustawianie kolejnosci tygodni treningowych
ustawianie kolejnosci cwiczen

LOGIKA:
Jesli ktos ma 3 rozne tygodnie to pokazuje 3 wstecz i 3 do przodu, jesli ktos ma 1 tylko to pokazuje 1 wstecz i 1 aktualny(jezeli jest skonczony to zaczyna nowy)
Jesli rozne tygodnie to lista tygodni na init, a jesli taki sam to od razu
zaznaczanie zrobionego tygodnia - jezeli wszystkie dni sa zrobione
zaznaczanie zrobionego dnia - jezeli wszystkie cwiczenia sa zrobione, pokazywanie daty "zrobione" jak sie wszystkie cwiczenia zrobi, a jak nie to bedzie
zaznaczanie zrobionego cwiczenia - jak sie przechodzi do kolejnego cwiczenia, a przy ostatnim cwiczeniu przy ostatniej serii guzik "Zakoncz cwiczenie"
wystwielac elementy w kolejnosci z gory na dol
stoper - wibracje po czasie i zapalanie ekranu
Wracanie do poprzedniego ekranu na Swipe z lewej do prawej SwipeDismissFrameLayout
klikniecie nowy tydzien/dzien/cwiczenie przenosi do wpisywania tekstu a potem do szczegolow danego elementu
jak dodaje na koniec dnia jakies kardio to wczytac to co zapisane i pozwolic na zmiane przed zakonczeniem dodawania

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

 */