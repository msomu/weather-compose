package dev.msomu.weathercompose.data.remote

import dev.msomu.weathercompose.BuildConfig
import dev.msomu.weathercompose.data.models.DailyForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast/daily")
    suspend fun getData(@Query("q")cityName : String,@Query("cnt") days : Int = 16, @Query("apiKey")apiKey : String = BuildConfig.API_KEY): Response<DailyForecast>
}