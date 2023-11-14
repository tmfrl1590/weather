package com.json.weatherforecast.screen.main

import androidx.lifecycle.ViewModel
import com.json.weatherforecast.data.DataOrException
import com.json.weatherforecast.model.Weather
import com.json.weatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    suspend fun getWeatherData(city: String, units: String,): DataOrException<Weather, Boolean, Exception>{
        return repository.getWeather(cityQuery = city, units = units)
    }
}