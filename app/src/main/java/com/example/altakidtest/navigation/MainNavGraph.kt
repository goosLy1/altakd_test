package com.example.altakidtest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altakidtest.presentation.home.FamilyAccountScreen
import com.example.altakidtest.presentation.home.ProfileScreen
import com.example.altakidtest.presentation.home.SettingsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, route = Graph.MAIN, startDestination = MainScreens.Profile.route, modifier = modifier) {
        composable(MainScreens.Profile.route) {
            ProfileScreen()
        }
        composable(MainScreens.FamilyAccount.route) {
            FamilyAccountScreen()
        }
        composable(MainScreens.Settings.route) {
            SettingsScreen(
                onNavigateToSignInScreen = {
                    navController.navigate(Graph.AUTH) {
                        popUpTo(Graph.MAIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        authNavGraph(navController)
    }
}