package com.example.fragments.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)

    fun getWeather(city: String, apiKey: String): Call<WeatherResponse> {
        return service.getWeather(city, apiKey)
    }
}