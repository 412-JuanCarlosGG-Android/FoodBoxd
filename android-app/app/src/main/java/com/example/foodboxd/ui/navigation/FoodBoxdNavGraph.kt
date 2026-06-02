package com.example.foodboxd.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodboxd.FoodBoxdApplication
import com.example.foodboxd.ui.ViewModelFactory
import com.example.foodboxd.ui.screens.detail.DetailScreen
import com.example.foodboxd.ui.screens.detail.DetailViewModel
import com.example.foodboxd.ui.screens.favorites.FavoritesScreen
import com.example.foodboxd.ui.screens.favorites.FavoritesViewModel
import com.example.foodboxd.ui.screens.home.HomeScreen
import com.example.foodboxd.ui.screens.home.HomeViewModel
import com.example.foodboxd.ui.screens.profile.ProfileScreen
import com.example.foodboxd.ui.screens.profile.ProfileViewModel
import com.example.foodboxd.ui.screens.ranking.RankingScreen
import com.example.foodboxd.ui.screens.ranking.RankingViewModel
import com.example.foodboxd.ui.screens.search.SearchScreen
import com.example.foodboxd.ui.screens.search.SearchViewModel

@Composable
def FoodBoxdNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as FoodBoxdApplication

    val mainDestinations = listOf(
        Screen.Home,
        Screen.Ranking,
        Screen.Search,
        Screen.Favorites,
        Screen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Show BottomBar only on main bottom screens
    val shouldShowBottomBar = mainDestinations.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                NavigationBar {
                    mainDestinations.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        NavigationBarItem(
                            icon = {
                                screen.icon?.let {
                                    Icon(imageVector = it, contentDescription = screen.title)
                                }
                            },
                            label = { Text(screen.title) },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                val viewModel: HomeViewModel = viewModel(factory = ViewModelFactory.provideHomeViewModel(app))
                HomeScreen(
                    viewModel = viewModel,
                    onRestaurantClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            
            composable(Screen.Ranking.route) {
                val viewModel: RankingViewModel = viewModel(factory = ViewModelFactory.provideRankingViewModel(app))
                RankingScreen(
                    viewModel = viewModel,
                    onRestaurantClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            
            composable(Screen.Search.route) {
                val viewModel: SearchViewModel = viewModel(factory = ViewModelFactory.provideSearchViewModel(app))
                SearchScreen(
                    viewModel = viewModel,
                    onRestaurantClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            
            composable(Screen.Favorites.route) {
                val viewModel: FavoritesViewModel = viewModel(factory = ViewModelFactory.provideFavoritesViewModel(app))
                FavoritesScreen(
                    viewModel = viewModel,
                    onRestaurantClick = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            
            composable(Screen.Profile.route) {
                val viewModel: ProfileViewModel = viewModel(factory = ViewModelFactory.provideProfileViewModel(app))
                ProfileScreen(viewModel = viewModel)
            }
            
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
            ) { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                val viewModel: DetailViewModel = viewModel(
                    factory = ViewModelFactory.provideDetailViewModel(app, restaurantId),
                    key = restaurantId // Using key to ensure new viewmodel per restaurant ID
                )
                DetailScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
