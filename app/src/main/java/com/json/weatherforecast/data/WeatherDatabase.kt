package com.json.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.json.weatherforecast.model.Favorite
import com.json.weatherforecast.model.Unit


@Database(
    entities = [Favorite::class, Unit::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}