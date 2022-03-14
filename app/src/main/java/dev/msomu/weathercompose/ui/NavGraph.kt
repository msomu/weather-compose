package dev.msomu.weathercompose.ui

import androidx.navigation.NavHostController
import dev.msomu.weathercompose.ui.Destinations.WeatherDetail
import dev.msomu.weathercompose.ui.Destinations.WeatherList

object Destinations {
    const val Home = "home"
    const val WeatherList = "cityWeatherList"
    const val WeatherDetail = "weatherDetail"

    object CityWeatherListArgs {
        const val CityName = "cityName"
    }
    object WeatherDetailArgs {
        const val Date = "date"
    }
}

class Actions(navController: NavHostController) {
    val openWeatherList: (String) -> Unit = { cityName ->
        navController.navigate("$WeatherList/$cityName")
    }
    val openWeatherDetail: (String, Int) -> Unit = { cityName, date ->
        navController.navigate("$WeatherDetail/$cityName/$date")
    }
    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }
}
