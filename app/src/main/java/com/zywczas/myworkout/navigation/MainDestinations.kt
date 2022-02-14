package com.zywczas.myworkout.navigation

sealed class MainDestinations(val route: String) {

    object Welcome : MainDestinations("Welcome")
    object WeeksList : MainDestinations("WeeksList")

}