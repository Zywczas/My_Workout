package com.zywczas.myworkout.watch.utils

/*
todo
2. dac cos ala dialog are you sure przed usuwaniem elementow
11. sprawdzic dimensy wszedzie
12. sprawdzic co to jest to SpeakableTextPresentCheck bo daje ignore we wszystkich layoutow
13. ustawic detekt tak zeby rowniez przy commitach skanowal kod
14. w timerze dac odliczanie czasu jako progress bar na zewnatrz kola
15. dac aktualna godzine w timerze
16. zmienic nazwe aplikacji na moj trening

Problem z timerem:
- jak ekran wygasa to wibracje tez sie zatrzymuja
- nie tworzy wogole ikonki... servisu... czasami tworzy, czasami nie, mozliwe ze za szybko minimalizuje aktywnosc, zanim zacznie sie serwis i nie odpala funkcji gotoforeground
- dzisiaj nagle mi sie wibracje wlaczyly jak otworzylem aplikacje i byl widok cwiczenia
- ram mialem blad ze nie mozna aktywowac serwisu z backgroundu
2 glowne problemy:
- jak juz raz stworzy ikonke ongoing activity to drugi raz jej nie uruchamia po ponownym przejsciu w background
- nie przywoluje aktywnosci po zakonczeniu czasu - bring aktivity to fron cos nie dziala

 */