package com.example.foodboxd

import android.app.Application
import com.example.foodboxd.di.AppContainer
import com.example.foodboxd.di.AppContainerImpl

class FoodBoxdApplication : Application() {
    
    // Contenedor global de dependencias de la aplicación
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl()
    }
}
