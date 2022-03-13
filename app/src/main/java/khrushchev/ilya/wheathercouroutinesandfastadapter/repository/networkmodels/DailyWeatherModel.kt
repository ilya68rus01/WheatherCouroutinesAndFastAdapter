package khrushchev.ilya.wheatherapp.models

import java.util.*

data class DailyWeatherModel(
    val date: Date,
    val description: String,
    val wind: Int,
    val pressure: Int,
    val temp: Int,
    val icon: String
)
