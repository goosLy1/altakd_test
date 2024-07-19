package com.example.altakidtest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.altakidtest.presentation.auth.signin.RestorePasswordScreen
import com.example.altakidtest.presentation.auth.signin.SignInScreen
import com.example.altakidtest.presentation.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreens.SignIn.route
    ) {
        composable(AuthScreens.SignIn.route) {
            SignInScreen(
                onNavigateToSignUpScreen = { navController.navigateToSingleTop(AuthScreens.SignUp.route) },
                onNavigateToHomeScreen = {navController.navigate(Graph.MAIN) {
                    popUpTo(Graph.AUTH) {
                        inclusive = true
                    }
                } },
                onNavigateToRestorePasswordScreen = {navController.navigateToSingleTop(AuthScreens.RestorePassword.route)}
            )
        }

        composable(AuthScreens.SignUp.route) {
            SignUpScreen(
                onNavigateToSignInScreen = {navController.navigateToSingleTop(AuthScreens.SignIn.route)}
            )
        }

        composable(AuthScreens.RestorePassword.route) {
            RestorePasswordScreen(
                onNavigateBack = {navController.navigateUp()}
            )
        }

    }
}

fun NavController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
//            inclusive = true

        }
        launchSingleTop = true
//        restoreState = true
    }
}