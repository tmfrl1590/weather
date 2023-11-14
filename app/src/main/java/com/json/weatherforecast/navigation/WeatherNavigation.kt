package com.json.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.json.weatherforecast.screen.main.MainScreen
import com.json.weatherforecast.screen.main.MainViewModel
import com.json.weatherforecast.screen.main.about.AboutScreen
import com.json.weatherforecast.screen.main.setting.SettingsScreen
import com.json.weatherforecast.screen.main.favorite.FavoriteScreen
import com.json.weatherforecast.screen.search.SearchScreen
import com.json.weatherforecast.screen.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        // www.google.com/cityname="seattle"
        val route = WeatherScreens.MainScreen.name
        composable(
            route ="$route/{city}",
            arguments = listOf(
                navArgument(name = "city"
                ){
                    type = NavType.StringType
                }
            )
        ){ navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name){
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingScreen.name){
            SettingsScreen(navController = navController)
        }
    }
}