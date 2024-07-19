package com.example.altakidtest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.altakidtest.ui.component.AltakidBottomAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            AltakidBottomAppBar(navController = navController)
        }
    ) { innerPadding ->
        MainNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}



//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(text = "Home Screen")
////        Text(text = currentUser?.id ?: "")
////        Text(text = currentUser?.email ?: "")
//            Button(onClick = {
//                viewModel.onSignOut()
//                onNavigateToSignInScreen()
//            }) {
//                Text(text = stringResource(id = R.string.sign_out_btn))
//            }
//        }