package com.pro.myapplication.route

sealed class RoutesScreen(val route: String) {
    data object Splash: RoutesScreen("SplashScreen")
    data object Login: RoutesScreen("LoginScreen")
    data object Principal: RoutesScreen("PrincipalScreen")
}