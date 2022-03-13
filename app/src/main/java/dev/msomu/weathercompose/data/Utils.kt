package dev.msomu.weathercompose.data

fun getDummyData(): DailyForecast {
    return DailyForecast(
        city = City(
            coord = Coord(
                lat = 0.0,
                lon = 0.0
            ), country = "India", id = 123, name = "Chennai", population = 0, timezone = 0
        ), cnt = 0, cod = "", list = listOf(
            DayWeather(
                clouds = 0,
                deg = 0,
                dt = 0,
                feels_like = FeelsLike(
                    day = 0,
                    eve = 0.0,
                    morn = 0.0,
                    night = 0.0
                ),
                gust = 0.0,
                humidity = 0,
                pop = 0,
                pressure = 0,
                speed = 0.0,
                sunrise = 0,
                sunset = 0,
                temp = Temp(
                    day = 0.0,
                    eve = 0.0,
                    max = 0.0,
                    min = 0.0,
                    morn = 0.0,
                    night = 0.0
                ),
                weather = listOf(Weather(description = "", icon = "", id = 0, main = ""))
            )
        ), message = 0.0
    )
}