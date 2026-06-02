package com.example.foodboxd.data.fake

import com.example.foodboxd.domain.model.Review
import com.example.foodboxd.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class FakeReviewRepository : ReviewRepository {

    private val reviewsState = MutableStateFlow(
        listOf(
            Review(
                id = "r1",
                restaurantId = "1",
                userId = "u2",
                userName = "Sofía Martínez",
                userAvatarUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=100&auto=format&fit=crop&q=60",
                rating = 5.0,
                comment = "La mejor pizza margarita que he probado fuera de Italia. La masa es sumamente ligera y el sabor de la albahaca fresca destaca. Definitivamente volveré.",
                date = "2026-05-28"
            ),
            Review(
                id = "r2",
                restaurantId = "1",
                userId = "u3",
                userName = "Carlos Mendoza",
                userAvatarUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=100&auto=format&fit=crop&q=60",
                rating = 4.5,
                comment = "Excelente ambiente familiar y gran servicio. Las pizzas salen rapidísimo del horno. El único detalle es que el local es un poco ruidoso en horas pico.",
                date = "2026-05-15"
            ),
            Review(
                id = "r3",
                restaurantId = "2",
                userId = "u4",
                userName = "Ana Gómez",
                userAvatarUrl = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=100&auto=format&fit=crop&q=60",
                rating = 4.0,
                comment = "El pescado es increíblemente fresco. El rollo Philadelphia estuvo delicioso, pero el ramen Tonkotsu estuvo un poco salado para mi gusto.",
                date = "2026-05-30"
            ),
            Review(
                id = "r4",
                restaurantId = "3",
                userId = "u2",
                userName = "Sofía Martínez",
                userAvatarUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=100&auto=format&fit=crop&q=60",
                rating = 4.5,
                comment = "Los tacos al pastor tienen muy buena sazón y la salsa verde está en su punto de picante. La música en vivo le da una excelente vibra.",
                date = "2026-06-01"
            ),
            Review(
                id = "r5",
                restaurantId = "4",
                userId = "u3",
                userName = "Carlos Mendoza",
                userAvatarUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=100&auto=format&fit=crop&q=60",
                rating = 5.0,
                comment = "¡La Truffle Burger es espectacular! El sabor a trufa es intenso y el pan brioche es tan suave que se derrite en la boca. Recomendado al 100%.",
                date = "2026-05-20"
            )
        )
    )

    override fun getReviewsForRestaurant(restaurantId: String): Flow<List<Review>> = 
        reviewsState.map { list -> list.filter { it.restaurantId == restaurantId } }

    override suspend fun addReview(
        restaurantId: String,
        userId: String,
        rating: Double,
        comment: String
    ): Review {
        // En una app real esto vendría de los datos del usuario logueado
        val newReview = Review(
            id = UUID.randomUUID().toString(),
            restaurantId = restaurantId,
            userId = userId,
            userName = "Usuario FoodBoxd", // Mock current user name
            userAvatarUrl = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&auto=format&fit=crop&q=60",
            rating = rating,
            comment = comment,
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        )
        val currentReviews = reviewsState.value.toMutableList()
        currentReviews.add(0, newReview) // Add at start of list
        reviewsState.value = currentReviews
        return newReview
    }
}
