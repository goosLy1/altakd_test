package com.example.altakidtest.presentation.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.altakidtest.R
import com.example.altakidtest.navigation.AuthScreens
import com.example.altakidtest.ui.component.AltakidTopAppBar
import com.example.altakidtest.ui.component.AuthForm

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
    onNavigateToSignUpScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToRestorePasswordScreen: () -> Unit
) {
    val signInUiState by viewModel.uiState.collectAsState()
    val isError = signInUiState.signInError != null
    val context = LocalContext.current
//    Log.e("ERROR", isError.toString())
    Scaffold(
        topBar = {
            AltakidTopAppBar(title = stringResource(id = AuthScreens.SignIn.title))
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isError) {
                Text(
                    text = signInUiState.signInError ?: stringResource(id = R.string.unknown_error),
                    color = MaterialTheme.colorScheme.error
                )
            }
            AuthForm(
                email = signInUiState.email,
                password = signInUiState.password,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                isError = isError
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(text = stringResource(id = R.string.req_fields))
//                , modifier = Modifier
////                    .align(alignment = Alignment.Start)
//                    .padding(
//                        start = dimensionResource(
//                            id = R.dimen.padding_medium
//                        )
//                    ))
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
                TextButton(onClick = onNavigateToRestorePasswordScreen) {
                    Text(text = stringResource(id = R.string.forgot_password))
                }
            }

            Button(
                onClick = {
                    viewModel.onSignIn(context)
                    //strange behavior o_O
                    if (!isError) {
                        onNavigateToHomeScreen()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        dimensionResource(id = R.dimen.padding_medium)
                    )
            ) {
                Text(text = stringResource(id = R.string.sign_in_btn))
            }
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.dont_have_an_account))
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
                TextButton(onClick = onNavigateToSignUpScreen) {
                    Text(text = stringResource(id = R.string.sign_up_btn))
                }
            }
            if (signInUiState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}