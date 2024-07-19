package com.example.altakidtest

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.altakidtest.navigation.Graph
import com.example.altakidtest.navigation.RootNavGraph
import com.example.altakidtest.presentation.auth.signin.SignInViewModel

@Composable
fun AltakidApp(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

//    Log.e("USER", viewModel.currentUser?.id ?: "")

    val currentUser = viewModel.currentUser
    println(currentUser)
    val startDestination = if (currentUser != null) Graph.MAIN else Graph.AUTH


//    AltakidBottomAppBar(navController = navController, startDestination = startDestination)
    RootNavGraph(navController = navController, startDestination = startDestination)
}