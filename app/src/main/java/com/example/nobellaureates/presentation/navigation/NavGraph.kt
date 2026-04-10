package com.example.nobellaureates.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nobellaureates.presentation.screen.detail.DetailScreen
import com.example.nobellaureates.presentation.screen.favorites.FavoritesScreen
import com.example.nobellaureates.presentation.screen.list.LaureateListScreen
import com.example.nobellaureates.presentation.screen.login.LoginScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object LaureateList : Screen("list")
    object Favorites : Screen("favorites")
    object Detail : Screen("detail/{laureateId}")
}

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.LaureateList.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }
        composable(Screen.LaureateList.route) {
            LaureateListScreen(
                navController = navController,
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onPrizeClick = { prizeId ->
                    navController.navigate("detail/$prizeId")
                }
            )
        }
        composable(
            Screen.Detail.route,
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