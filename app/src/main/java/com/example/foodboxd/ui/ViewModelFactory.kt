package com.example.foodboxd.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodboxd.FoodBoxdApplication
import com.example.foodboxd.ui.screens.home.HomeViewModel
import com.example.foodboxd.ui.screens.ranking.RankingViewModel
import com.example.foodboxd.ui.screens.search.SearchViewModel
import com.example.foodboxd.ui.screens.favorites.FavoritesViewModel
import com.example.foodboxd.ui.screens.profile.ProfileViewModel
import com.example.foodboxd.ui.screens.detail.DetailViewModel

/**
 * Proveedor de fábricas de ViewModels para la inyección manual de dependencias.
 */
object ViewModelFactory {

    fun provideHomeViewModel(app: FoodBoxdApplication) = viewModelFactory {
        initializer {
            HomeViewModel(app.container.restaurantRepository)
        }
    }

    fun provideRankingViewModel(app: FoodBoxdApplication) = viewModelFactory {
        initializer {
            RankingViewModel(app.container.restaurantRepository)
        }
    }

    fun provideSearchViewModel(app: FoodBoxdApplication) = viewModelFactory {
        initializer {
            SearchViewModel(app.container.restaurantRepository)
        }
    }

    fun provideFavoritesViewModel(app: FoodBoxdApplication) = viewModelFactory {
        initializer {
            FavoritesViewModel(app.container.restaurantRepository)
        }
    }

    fun provideProfileViewModel(app: FoodBoxdApplication) = viewModelFactory {
        initializer {
            ProfileViewModel(app.container.userRepository)
        }
    }

    fun provideDetailViewModel(app: FoodBoxdApplication, restaurantId: String) = viewModelFactory {
        initializer {
            DetailViewModel(
                restaurantId = restaurantId,
                restaurantRepository = app.container.restaurantRepository,
                reviewRepository = app.container.reviewRepository,
                userRepository = app.container.userRepository
            )
        }
    }
}
