package com.example.foodboxd.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodboxd.domain.model.Category
import com.example.foodboxd.domain.model.Restaurant
import com.example.foodboxd.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
        restaurantRepository.getCategories(),
        restaurantRepository.getFeaturedRestaurants(),
        restaurantRepository.getRecommendedRestaurants(),
        restaurantRepository.getPromotions()
    ) { categories, featured, recommended, promotions ->
        HomeUiState.Success(
            categories = categories,
            featured = featured,
            recommended = recommended,
            promotions = promotions
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState.Loading
    )
}

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val categories: List<Category>,
        val featured: List<Restaurant>,
        val recommended: List<Restaurant>,
        val promotions: List<Restaurant>
    ) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
