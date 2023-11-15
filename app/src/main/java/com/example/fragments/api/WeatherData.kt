package com.example.fragments.api

import java.math.BigInteger

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val sys: Sys,
    val name: String
)

data class Main(
    val temp: Double
)
data class Sys(
    val sunrise: Long,
    val sunset: Long
)

data class Weather(
    val description: String
)
