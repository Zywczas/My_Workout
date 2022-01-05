package com.zywczas.myworkout.watch.utils

/*
todo
0. dac automatyczne przechodzenie z pierwszego ekranu
1. za dlugo wczytuje liste i nie potrzebnie pokazuje sie ekran z info o pustym treningu - sprawdzic czy to wystepuje jak normalnie uruchamiam aplikacje, czy jak tylko debuguje ja
2. dac cos ala dialog are you sure przed usuwaniem elementow
3. Dac ikonke
4. Progress bar jakos dziwnie wyglada
5. rozpocznij cwiczenia / kontynuuj cwiczenia zle sie wyswietla
6. nie przechodzi poprawnie do kolejnego cwiczenia
7. timer wczytuje tylko 2 sekundy zamiast 60 domyslnych
8. crash jak podczas timera wcisnalem guzik back, ale bylem w debuggerze wiec moze to zawadzilo:
java.lang.RuntimeException: Unable to create service com.zywczas.myworkout.watch.services.timer.TimerService: java.lang.IllegalStateException: Not allowed to start service Intent
{ cmp=com.zywczas.myworkout.watch/.services.timer.TimerService }: app is in background uid UidRecord{17447 u0a65 SVC  idle change:uncached procs:1 seq(0,0,0)}
9. przy dodawaniu cardio nie oznacza dnia jako rozpoczetego
 */