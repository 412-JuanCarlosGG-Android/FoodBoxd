package com.example.foodboxd.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Ranking : Screen("ranking", "Ranking", Icons.Default.Star)
    object Search : Screen("search", "Buscar", Icons.Default.Search)
    object Favorites : Screen("favorites", "Favoritos", Icons.Default.Favorite)
    object Profile : Screen("profile", "Usuario", Icons.Default.Person)
    
    object Detail : Screen("detail/{restaurantId}", "Detalle") {
        fun createRoute(restaurantId: String) = "detail/$restaurantId"
    }
}
