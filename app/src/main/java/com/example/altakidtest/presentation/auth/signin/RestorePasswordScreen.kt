package com.example.altakidtest.presentation.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.altakidtest.navigation.AuthScreens
import com.example.altakidtest.ui.component.AltakidTopAppBar

@Composable
fun RestorePasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

//    var email by rememberSaveable {
//        mutableStateOf("")
//    }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AltakidTopAppBar(
                title = stringResource(id = AuthScreens.RestorePassword.title),
                canNavigateBack = true,
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Restore password")
            OutlinedTextField(value = uiState.email, onValueChange = viewModel::onEmailChange)
            Button(onClick = {
                viewModel.onResetPassword(uiState.email)
            }) {
                Text(text = "restore")
            }
        }
    }
}