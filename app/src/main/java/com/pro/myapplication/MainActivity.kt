package com.pro.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pro.myapplication.login.ui.LoginScreen
import com.pro.myapplication.login.ui.LoginViewModel
import com.pro.myapplication.principal.ui.PrincipalScreen
import com.pro.myapplication.route.RoutesScreen
import com.pro.myapplication.splash.ui.SplashScreen
import com.pro.myapplication.ui.theme.MyAplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = RoutesScreen.Login.route
                    ) {
                        composable(RoutesScreen.Splash.route) {
                            SplashScreen()
                        }
                        composable(RoutesScreen.Login.route, enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up, tween(400)
                            )
                        }, exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down,
                                tween(400)
                            )
                        }) {
                            /* val keyData =
                                 navigationController.currentBackStackEntry?.savedStateHandle?.get<Boolean>(
                                     "isBack"
                                 ) ?: false*/
                            LoginScreen(loginViewModel) {
                                navigationController.navigate(RoutesScreen.Principal.route) {
                                    popUpTo(navigationController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                        composable(RoutesScreen.Principal.route) {
                            /*  val navBackStackEntry by navigationController.currentBackStackEntryAsState()
                              val savedStateHandle = navBackStackEntry?.savedStateHandle

                              savedStateHandle?.set("isBack", true)*/
                            PrincipalScreen()
                        }
                    }
                }
            }
        }
    }
}