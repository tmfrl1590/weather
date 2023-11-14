package com.json.weatherforecast.repository

import android.util.Log
import com.json.weatherforecast.data.DataOrException
import com.json.weatherforecast.model.Weather
import com.json.weatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
){
    suspend fun getWeather(cityQuery: String, units: String,): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery, units = units)
        }catch (e: Exception){
            return DataOrException(e = e)
        }

        Log.i("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }
}