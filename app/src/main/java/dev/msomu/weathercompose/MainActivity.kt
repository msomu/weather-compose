package dev.msomu.weathercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import dev.msomu.weathercompose.ui.Actions
import dev.msomu.weathercompose.ui.Destinations.CityWeatherListArgs.CityName
import dev.msomu.weathercompose.ui.Destinations.Home
import dev.msomu.weathercompose.ui.Destinations.WeatherDetail
import dev.msomu.weathercompose.ui.Destinations.WeatherDetailArgs.Date
import dev.msomu.weathercompose.ui.Destinations.WeatherList
import dev.msomu.weathercompose.ui.screen.HomeScreen
import dev.msomu.weathercompose.ui.screen.WeatherDetailScreen
import dev.msomu.weathercompose.ui.screen.WeatherListScreen
import dev.msomu.weathercompose.ui.theme.WeatherComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp() {
    WeatherComposeTheme {
        ProvideWindowInsets {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                val actions = remember(navController) { Actions(navController) }
                NavHost(navController = navController, startDestination = Home) {
                    composable(Home) { HomeScreen(actions.openWeatherList) }
                    composable(
                        "$WeatherList/{$CityName}",
                        arguments = listOf(navArgument(CityName) { type = NavType.StringType })
                    ) { backStackEntry ->
                        WeatherListScreen(
                            cityName = backStackEntry.arguments?.getString(
                                CityName
                            ) ?: "",
                            openWeatherDetail = actions.openWeatherDetail
                        )
                    }
                    composable(
                        "$WeatherDetail/{$CityName}/{$Date}",
                        arguments = listOf(navArgument(CityName) { type = NavType.StringType },
                            navArgument(Date) { type = NavType.StringType })
                    ) { backStackEntry ->
                        WeatherDetailScreen(
                            cityName = backStackEntry.arguments?.getString(
                                CityName
                            ) ?: "",
                            date = backStackEntry.arguments?.getString(
                                Date
                            ) ?: ""
                        )
                    }
                }
            }
        }
    }
}