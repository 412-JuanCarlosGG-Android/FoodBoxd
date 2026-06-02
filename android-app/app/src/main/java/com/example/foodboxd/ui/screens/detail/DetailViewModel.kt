package com.example.foodboxd.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodboxd.domain.model.Restaurant
import com.example.foodboxd.domain.model.Review
import com.example.foodboxd.domain.repository.RestaurantRepository
import com.example.foodboxd.domain.repository.ReviewRepository
import com.example.foodboxd.domain.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val restaurantId: String,
    private val restaurantRepository: RestaurantRepository,
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userReviewComment = MutableStateFlow("")
    val userReviewComment = _userReviewComment.asStateFlow()

    private val _userReviewRating = MutableStateFlow(5.0)
    val userReviewRating = _userReviewRating.asStateFlow()

    val uiState: StateFlow<DetailUiState> = combine(
        restaurantRepository.getRestaurantById(restaurantId),
        reviewRepository.getReviewsForRestaurant(restaurantId)
    ) { restaurant, reviews ->
        if (restaurant != null) {
            DetailUiState.Success(restaurant, reviews)
        } else {
            DetailUiState.Error("Restaurante no encontrado")
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailUiState.Loading
    )

    fun onToggleFavorite(isFavorite: Boolean) {
        viewModelScope.launch {
            restaurantRepository.toggleFavorite(restaurantId, isFavorite)
        }
    }

    fun onCommentChange(comment: String) {
        _userReviewComment.value = comment
    }

    fun onRatingChange(rating: Double) {
        _userReviewRating.value = rating
    }

    fun submitReview() {
        val comment = _userReviewComment.value
        val rating = _userReviewRating.value
        if (comment.isBlank()) return

        viewModelScope.launch {
            // Get current user id (mocked as 'u1')
            val user = userRepository.getCurrentUser().firstOrNull()
            val userId = user?.id ?: "u1"
            
            reviewRepository.addReview(
                restaurantId = restaurantId,
                userId = userId,
                rating = rating,
                comment = comment
            )
            
            // Clear input fields
            _userReviewComment.value = ""
            _userReviewRating.value = 5.0
        }
    }
}

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val restaurant: Restaurant, val reviews: List<Review>) : DetailUiState
    data class Error(val message: String) : DetailUiState
}
