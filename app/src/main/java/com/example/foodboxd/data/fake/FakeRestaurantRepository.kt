package com.example.foodboxd.data.fake

import com.example.foodboxd.domain.model.Category
import com.example.foodboxd.domain.model.MenuItem
import com.example.foodboxd.domain.model.Restaurant
import com.example.foodboxd.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FakeRestaurantRepository : RestaurantRepository {

    private val categories = listOf(
        Category("cat1", "Italiana", "https://images.unsplash.com/photo-1513104890138-7c749659a591"),
        Category("cat2", "Sushi", "https://images.unsplash.com/photo-1579871494447-9811cf80d66c"),
        Category("cat3", "Mexicana", "https://images.unsplash.com/photo-1565299585323-38d6b0865b47"),
        Category("cat4", "Hamburguesas", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd"),
        Category("cat5", "Cafetería", "https://images.unsplash.com/photo-1509042239860-f550ce710b93"),
        Category("cat6", "Postres", "https://images.unsplash.com/photo-1551024601-bec78aea704b")
    )

    private val restaurantsState = MutableStateFlow(
        listOf(
            Restaurant(
                id = "1",
                name = "Pizzería Bella Italia",
                imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=500&auto=format&fit=crop&q=60",
                rating = 4.8,
                reviewCount = 142,
                category = "Italiana",
                description = "Auténtica pizza napolitana al horno de leña, pastas frescas elaboradas en casa y una selecta carta de vinos italianos.",
                isFavorite = false,
                menuItems = listOf(
                    MenuItem("m11", "Pizza Margarita", 12.50, "Salsa de tomate casera, mozzarella fresca, albahaca y aceite de oliva virgen.", "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m12", "Lasaña de la Nona", 14.50, "Capas de pasta artesanal con ragú de ternera, bechamel cremosa y queso gratinado.", "https://images.unsplash.com/photo-1574894709920-11b28e7367e3?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m13", "Tiramisú Clásico", 6.50, "Bizcocho de soletilla bañado en café espresso, licor amaretto y crema mascarpone.", "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=300&auto=format&fit=crop&q=60")
                )
            ),
            Restaurant(
                id = "2",
                name = "Sushi Roll & Sake",
                imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=500&auto=format&fit=crop&q=60",
                rating = 4.5,
                reviewCount = 98,
                category = "Sushi",
                description = "Sushi premium elaborado por itamaes expertos con ingredientes frescos e importados del día.",
                isFavorite = true,
                menuItems = listOf(
                    MenuItem("m21", "Philly Roll (10 pzs)", 10.90, "Salmón, queso crema y aguacate, cubierto con ajonjolí tostado.", "https://images.unsplash.com/photo-1611143669185-af224c5e3252?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m22", "Dragon Roll (10 pzs)", 13.50, "Ebi tempura y pepino por dentro, cubierto de láminas de aguacate y salsa anguila.", "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m23", "Ramen Tonkotsu", 15.00, "Caldo concentrado de cerdo de 12 horas, fideos caseros, chashu, huevo ajitama.", "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?w=300&auto=format&fit=crop&q=60")
                )
            ),
            Restaurant(
                id = "3",
                name = "El Taco Loco",
                imageUrl = "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=500&auto=format&fit=crop&q=60",
                rating = 4.6,
                reviewCount = 210,
                category = "Mexicana",
                description = "El verdadero sabor de la calle mexicana. Tacos al pastor, margaritas refrescantes y ambiente festivo.",
                isFavorite = false,
                menuItems = listOf(
                    MenuItem("m31", "Orden de Tacos al Pastor (4 pzs)", 8.00, "Tortilla de maíz azul, carne de cerdo marinada, piña, cilantro y cebolla.", "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m32", "Guacamole Tradicional", 7.50, "Aguacate machacado con pico de gallo, lima, acompañado de totopos caseros.", "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?w=300&auto=format&fit=crop&q=60")
                )
            ),
            Restaurant(
                id = "4",
                name = "The Burger House",
                imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500&auto=format&fit=crop&q=60",
                rating = 4.9,
                reviewCount = 325,
                category = "Hamburguesas",
                description = "Hamburguesas artesanales de carne 100% de res Angus madurada, pan brioche y combinaciones gourmet.",
                isFavorite = false,
                menuItems = listOf(
                    MenuItem("m41", "Truffle Burger", 13.90, "Carne Angus (180g), queso provolone, champiñones salteados y mayonesa de trufa.", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m42", "Smoked Bacon Burger", 12.50, "Carne Angus, doble queso cheddar, tocino ahumado crujiente, cebolla caramelizada.", "https://images.unsplash.com/photo-1553979459-d2229ba7433b?w=300&auto=format&fit=crop&q=60")
                )
            ),
            Restaurant(
                id = "5",
                name = "The Daily Grind",
                imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=500&auto=format&fit=crop&q=60",
                rating = 4.3,
                reviewCount = 84,
                category = "Cafetería",
                description = "Café de especialidad de origen ético, repostería horneada diariamente y un espacio acogedor para trabajar.",
                isFavorite = true,
                menuItems = listOf(
                    MenuItem("m51", "Flat White", 3.80, "Doble shot de espresso con leche texturizada de manera sedosa.", "https://images.unsplash.com/photo-1577968897966-3d4325b36b61?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m52", "Croissant de Almendra", 4.20, "Hojaldre de mantequilla relleno de crema frangipane y almendras fileteadas tostadas.", "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=300&auto=format&fit=crop&q=60")
                )
            ),
            Restaurant(
                id = "6",
                name = "La Dicha Dulce",
                imageUrl = "https://images.unsplash.com/photo-1551024601-bec78aea704b?w=500&auto=format&fit=crop&q=60",
                rating = 4.7,
                reviewCount = 115,
                category = "Postres",
                description = "El paraíso de los amantes del dulce. Pasteles de diseño, waffles, helados artesanales y batidos gigantes.",
                isFavorite = false,
                menuItems = listOf(
                    MenuItem("m61", "Cheesecake de Frutos Rojos", 5.90, "Tarta de queso horneada al estilo Nueva York con compota casera de frutos del bosque.", "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=300&auto=format&fit=crop&q=60"),
                    MenuItem("m62", "Waffle de Chocolate Supremo", 7.00, "Waffle recién hecho con nutella, fresas frescas y helado de vainilla de Madagascar.", "https://images.unsplash.com/photo-1562376502-6f769499c886?w=300&auto=format&fit=crop&q=60")
                )
            )
        )
    )

    override fun getCategories(): Flow<List<Category>> = flow {
        emit(categories)
    }

    override fun getFeaturedRestaurants(): Flow<List<Restaurant>> = 
        restaurantsState.map { list -> list.filter { it.rating >= 4.7 }.take(3) }

    override fun getRecommendedRestaurants(): Flow<List<Restaurant>> = 
        restaurantsState.map { list -> list.takeLast(3) }

    override fun getPromotions(): Flow<List<Restaurant>> = 
        restaurantsState.map { list -> list.filter { it.id == "3" || it.id == "5" } }

    override fun getTopRankedRestaurants(): Flow<List<Restaurant>> = 
        restaurantsState.map { list -> list.sortedByDescending { it.rating } }

    override fun searchRestaurants(query: String): Flow<List<Restaurant>> = 
        restaurantsState.map { list -> 
            if (query.isBlank()) {
                list
            } else {
                list.filter { 
                    it.name.contains(query, ignoreCase = true) || 
                    it.category.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
                } 
            }
        }

    override fun getFavorites(): Flow<List<Restaurant>> = 
        restaurantsState.map { list -> list.filter { it.isFavorite } }

    override fun getRestaurantById(id: String): Flow<Restaurant?> = 
        restaurantsState.map { list -> list.find { it.id == id } }

    override fun toggleFavorite(id: String, isFavorite: Boolean) {
        val currentList = restaurantsState.value
        val updated = currentList.map {
            if (it.id == id) it.copy(isFavorite = isFavorite) else it
        }
        restaurantsState.value = updated
    }
}
