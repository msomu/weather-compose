package dev.msomu.weathercompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherDisplayData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}