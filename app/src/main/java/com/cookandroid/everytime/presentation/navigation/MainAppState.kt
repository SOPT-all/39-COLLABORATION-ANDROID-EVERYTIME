package com.cookandroid.everytime.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainAppState(
    val navController: NavHostController
)

@Composable
fun rememberMainAppState(): MainAppState {
    val navController = rememberNavController()

    return remember(navController) {
        MainAppState(navController)
    }
}