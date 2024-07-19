package com.example.altakidtest.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.altakidtest.navigation.MainScreens
import com.example.altakidtest.ui.component.AltakidTopAppBar

@Composable
fun FamilyAccountScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            AltakidTopAppBar(title = stringResource(id = MainScreens.FamilyAccount.titleResId))
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Family Account")
//        Text(text = currentUser?.id ?: "")
//        Text(text = currentUser?.email ?: "")
//            Button(onClick = {
//                viewModel.onSignOut()
//                onNavigateToSignInScreen()
//            }) {
//                Text(text = stringResource(id = R.string.sign_out_btn))
//            }
        }
    }
}