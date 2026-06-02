package com.example.foodboxd.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodboxd.domain.model.Restaurant
import com.example.foodboxd.domain.repository.RestaurantRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class SearchViewModel(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val uiState: StateFlow<SearchUiState> = _searchQuery
        .debounce(300) // Debounce searching to avoid executing on every keystroke
        .flatMapLatest { query ->
            restaurantRepository.searchRestaurants(query)
                .map { list -> SearchUiState.Success(list) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SearchUiState.Loading
        )

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }
}

sealed interface SearchUiState {
    object Loading : SearchUiState
    data class Success(val results: List<Restaurant>) : SearchUiState
    data class Error(val message: String) : SearchUiState
}
