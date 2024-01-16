package com.aliahmed.weatherapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aliahmed.core_ui.NavigationItem
import com.aliahmed.current_weather.view.CurrentWeatherScreen
import com.aliahmed.forecasting.view.ForecastingScreen
import com.example.news.view.NewsScreen

@Composable
fun Navigation (navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.News.route){
        composable(NavigationItem.News.route){
            NewsScreen(navController)
        }

        composable(NavigationItem.CurrentWeather.route){
            CurrentWeatherScreen(navController)
        }

        composable(NavigationItem.Forecasting.route){
            ForecastingScreen(navController)
        }

    }
}