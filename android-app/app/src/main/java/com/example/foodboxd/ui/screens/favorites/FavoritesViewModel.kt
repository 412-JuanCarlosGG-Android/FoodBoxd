package com.example.foodboxd.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodboxd.domain.model.Restaurant
import com.example.foodboxd.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    val uiState: StateFlow<FavoritesUiState> = restaurantRepository.getFavorites()
        .map { list -> FavoritesUiState.Success(list) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = FavoritesUiState.Loading
        )

    fun onRemoveFavorite(restaurantId: String) {
        viewModelScope.launch {
            restaurantRepository.toggleFavorite(restaurantId, false)
        }
    }
}

sealed interface FavoritesUiState {
    object Loading : FavoritesUiState
    data class Success(val favorites: List<Restaurant>) : FavoritesUiState
    data class Error(val message: String) : FavoritesUiState
}
