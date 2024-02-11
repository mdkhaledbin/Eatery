package com.example.resturantrowviews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resturantrowviews.ui.theme.ResturantRowViewsTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    val viewModel = ResturantRowsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            ResturantRowViewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "ResturantRowsView"
                    ) {
                        composable("ResturantRowsView") {
                            ResturantRowsView(
                                navController,
                                viewModel.resturantList
                            )
                        }
                    }
                }
            }
        }
    }
}
