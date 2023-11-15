package com.example.fragments.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragments.api.WeatherRepository
import com.example.fragments.api.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> = _weatherData

    fun loadWeather(city: String) {
        val apiKey = "e244adefd4fc0fc797f7d468b3e87bf8"
        repository.getWeather(city, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("WeatherViewModel", "Error fetching weather data", t)
            }
        })
    }
}
