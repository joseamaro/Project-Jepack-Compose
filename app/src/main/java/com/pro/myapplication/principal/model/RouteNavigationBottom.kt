package com.pro.myapplication.principal.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class RouteNavigationBottom(
    var title: String,
    var icon: ImageVector,
    val route: String
) {
    data object Home :
        RouteNavigationBottom(
            title = "Home",
            icon = Icons.Default.Home,
            route = "Home"
        )

    data object Register :
        RouteNavigationBottom(
            title = "Registros",
            icon = Icons.Default.Archive,
            route = "Register"
        )

    data object Profile :
        RouteNavigationBottom(
            title = "Perfil",
            icon = Icons.Default.Person,
            route = "Profile"
        )
}