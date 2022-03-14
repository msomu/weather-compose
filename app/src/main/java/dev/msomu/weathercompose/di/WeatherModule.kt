package dev.msomu.weathercompose.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.msomu.weathercompose.Constants.BASE_URL
import dev.msomu.weathercompose.Constants.DATABASE_NAME
import dev.msomu.weathercompose.data.local.WeatherDao
import dev.msomu.weathercompose.data.local.WeatherDatabase
import dev.msomu.weathercompose.data.remote.WeatherApi
import dev.msomu.weathercompose.repository.DefaultWeatherRepository
import dev.msomu.weathercompose.repository.WeatherRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient
    = OkHttpClient.Builder()
    .addInterceptor(CurlInterceptor {
        Log.v("HttpCall", it)
    })
    .build();

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): WeatherDatabase =
        Room.databaseBuilder(app, WeatherDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideRepository(
        api: WeatherApi,
        database: WeatherDatabase,
        weatherDao: WeatherDao,
    ): WeatherRepository = DefaultWeatherRepository(api, database, weatherDao)

    @Singleton
    @Provides
    fun provideIssueDao(
        database: WeatherDatabase
    ) = database.weatherDao()
}