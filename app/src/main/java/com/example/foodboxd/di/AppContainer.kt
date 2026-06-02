package com.example.foodboxd.di

import com.example.foodboxd.data.fake.FakeRestaurantRepository
import com.example.foodboxd.data.fake.FakeReviewRepository
import com.example.foodboxd.data.fake.FakeUserRepository
import com.example.foodboxd.domain.repository.RestaurantRepository
import com.example.foodboxd.domain.repository.ReviewRepository
import com.example.foodboxd.domain.repository.UserRepository

/**
 * Contenedor de dependencias para inyección manual.
 * Facilita el cambio entre implementaciones fake y reales sin alterar el resto del proyecto.
 */
interface AppContainer {
    val restaurantRepository: RestaurantRepository
    val reviewRepository: ReviewRepository
    val userRepository: UserRepository
}

class AppContainerImpl : AppContainer {
    override val restaurantRepository: RestaurantRepository by lazy {
        FakeRestaurantRepository()
    }

    override val reviewRepository: ReviewRepository by lazy {
        FakeReviewRepository()
    }

    override val userRepository: UserRepository by lazy {
        FakeUserRepository()
    }
}
