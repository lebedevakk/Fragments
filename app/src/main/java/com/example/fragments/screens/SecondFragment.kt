package com.example.fragments.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fragments.constants.MAIN
import com.example.fragments.R
import com.example.fragments.databinding.FragmentSecondBinding
import com.example.fragments.viewModels.WeatherViewModel
import java.lang.Math.round
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: WeatherViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        arguments?.let { bundle ->
            val selectedItem = bundle.getString("selected_item", "")
            viewModel.loadWeather(selectedItem)
        }
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { weatherResponse ->
            binding.city.text = weatherResponse.name
            binding.temperature.text =  "Temparature: " + kelvinToCelsius(weatherResponse.main.temp)
            binding.sunset.text = "Sunset in:" + formattedTime(weatherResponse.sys.sunset)
            binding.sunrise.text = "Sunrise in:"+ formattedTime(weatherResponse.sys.sunrise)

        })
        binding.buttonBackToFragment.setOnClickListener {
            MAIN.navController.navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }
    fun kelvinToCelsius(kelvin: Double) = round(kelvin - 273.15)
    fun formattedTime(unixTime : Long): String{
        val instant = Instant.ofEpochSecond(unixTime)

        // Устанавливаем часовой пояс GMT+02:00
        val zoneId = ZoneId.of("GMT+02:00")

        // Преобразуем объект Instant в формат ЧАСЫ:МИНУТЫ
        val formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(zoneId)
        return formatter.format(instant)
    }
}