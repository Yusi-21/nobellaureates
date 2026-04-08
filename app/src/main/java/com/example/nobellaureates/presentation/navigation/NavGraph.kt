package com.example.nobellaureates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nobellaureates.presentation.screen.detail.DetailScreen
import com.example.nobellaureates.presentation.screen.list.LaureateListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            LaureateListScreen(navController = navController)
        }

        composable(
            "detail/{laureateId}",
            arguments = listOf(navArgument("laureateId") { type = NavType.StringType })
        ) { backStackEntry ->
            val laureateId = backStackEntry.arguments?.getString("laureateId") ?: ""
            DetailScreen(
                navController = navController,
                laureateId = laureateId
            )
        }
    }
}