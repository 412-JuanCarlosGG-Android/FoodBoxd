package com.example.foodboxd.ui.screens.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodboxd.domain.model.Restaurant
import com.example.foodboxd.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RankingViewModel(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    val uiState: StateFlow<RankingUiState> = restaurantRepository.getTopRankedRestaurants()
        .map { list -> RankingUiState.Success(list) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RankingUiState.Loading
        )
}

sealed interface RankingUiState {
    object Loading : RankingUiState
    data class Success(val restaurants: List<Restaurant>) : RankingUiState
    data class Error(val message: String) : RankingUiState
}
