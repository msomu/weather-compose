package dev.msomu.weathercompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_data WHERE cityName = :cityName")
    fun getAllWeatherData(cityName : String): Flow<List<WeatherDisplayData>>

    @Query("SELECT * FROM weather_data WHERE cityName = :cityName AND dt = :dt")
    fun getAllWeatherData(cityName : String, dt : Int): Flow<WeatherDisplayData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(issues: List<WeatherDisplayData>)

    @Query("DELETE FROM weather_data")
    suspend fun deleteAllData()
}