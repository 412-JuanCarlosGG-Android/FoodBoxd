package com.example.foodboxd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.foodboxd.ui.navigation.FoodBoxdNavGraph
import com.example.foodboxd.ui.theme.FoodBoxdTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodBoxdTheme {
                FoodBoxdNavGraph(modifier = Modifier.fillMaxSize())
            }
        }
    }
}