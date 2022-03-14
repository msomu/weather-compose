package dev.msomu.weathercompose.repository

import android.util.Log
import androidx.room.withTransaction
import dev.msomu.weathercompose.data.local.WeatherDao
import dev.msomu.weathercompose.data.local.WeatherDatabase
import dev.msomu.weathercompose.data.local.WeatherDisplayData
import dev.msomu.weathercompose.data.remote.WeatherApi
import dev.msomu.weathercompose.util.networkBoundResource
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val db: WeatherDatabase,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override fun getWeather(cityName: String) = networkBoundResource(
        shouldFetch = {true},
        query = {
            weatherDao.getAllWeatherData(cityName)
        },
        fetch = {
            val data = api.getData(cityName)
            Log.d("WeatherRepository","data is back ${data.isSuccessful}")
            if (data.isSuccessful && data.body() != null) {
                Log.d("WeatherRepository","Data is not empty ${data.body()}")
                data.body()!!
            } else {
                Log.e("WeatherRepository","Success: ${data.isSuccessful} error: ${data.message()} ")
                throw Error(data.message())
            }
        },
        saveFetchResult = { data ->
                db.withTransaction {
                    weatherDao.deleteAllData()
                    weatherDao.insertData(data.list.map {
                        WeatherDisplayData(
                            dt = it.dt,
                            cityName = cityName,
                            temp = it.temp.day,
                            feelsLike = it.feels_like.day,
                            weatherMain = it.weather.first().main,
                            weatherDescription = it.weather.first().description
                        )
                    })
                }
        },
    )

    override fun getWeather(cityName: String, dt: Int) = networkBoundResource(
        shouldFetch = {false},
        query = {
            weatherDao.getAllWeatherData(cityName, dt)
        },
        fetch = {
            val res = api.getData(cityName)
            if (res.isSuccessful && res.body() != null) {
                res.body()!!
            } else {
                throw Error("")
            }
        },
        saveFetchResult = { data ->
                db.withTransaction {
                    weatherDao.deleteAllData()
                    weatherDao.insertData(data.list.map {
                        WeatherDisplayData(
                            dt = it.dt,
                            cityName = cityName,
                            temp = it.temp.day,
                            feelsLike = it.feels_like.day,
                            weatherMain = it.weather.first().main,
                            weatherDescription = it.weather.first().description
                        )
                    })
                }
        }
    )

}