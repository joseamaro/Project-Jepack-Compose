package com.pro.myapplication.principal.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pro.myapplication.home.HomeScreen
import com.pro.myapplication.principal.model.RouteNavigationBottom
import com.pro.myapplication.profile.ProfileScreen
import com.pro.myapplication.register.RegisterScreen
import com.pro.myapplication.ui.theme.colorBlue60

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrincipalScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBottomMenu(navController = navController)
    }) {
        NavHost(navController = navController, startDestination = RouteNavigationBottom.Home.route) {
            composable(RouteNavigationBottom.Home.route) { HomeScreen() }
            composable(RouteNavigationBottom.Register.route) { RegisterScreen() }
            composable(RouteNavigationBottom.Profile.route) { ProfileScreen() }
        }
    }
}

@Composable
fun NavigationBottomMenu(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(RouteNavigationBottom.Home, RouteNavigationBottom.Register, RouteNavigationBottom.Profile)
    NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = colorBlue60) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White,
                    selectedIconColor = Color.Black,
                    unselectedTextColor = Color.White,
                    selectedTextColor = Color.Black
                )
            )
        }
    }
}