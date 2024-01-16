package com.aliahmed.core_ui

sealed class NavigationItem(val route: String, val icon: Int, val title: String) {
    object News :
        NavigationItem(Routes.news, R.drawable.current_weather, "News")
    object CurrentWeather :
        NavigationItem(Routes.weather, R.drawable.current_weather, "Current Weather")

    object Forecasting :
        NavigationItem(Routes.forecasting, R.drawable.weather_forecasting, "Forecasting")

}
