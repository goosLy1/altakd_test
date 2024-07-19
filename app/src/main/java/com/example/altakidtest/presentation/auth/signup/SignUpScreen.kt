package com.example.altakidtest.presentation.auth.signup

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

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignUpScreen(
//    modifier: Modifier = Modifier,
//    viewModel: SignUpViewModel = hiltViewModel()
//) {
//    val coroutineScope = rememberCoroutineScope()
//    val snackBarHostState = remember {
//        SnackbarHostState()
//    }
//
//    Scaffold(
//        snackbarHost = { SnackbarHost(hostState = snackBarHostState)},
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Sign Up") },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary
//                )
//            )
//        }
//    ) {innerPadding ->
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            val email = viewModel.email.collectAsState(initial = "")
//            val password = viewModel.password.collectAsState("")
//            val message by viewModel.message.collectAsState()
//            OutlinedTextField(
//                label = {
//                    Text(
//                        text = "Email",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                },
//                maxLines = 1,
//                shape = RoundedCornerShape(32),
//                modifier = modifier.fillMaxWidth(),
//                value = email.value,
//                onValueChange = {
//                    viewModel.onEmailChange(it)
//                },
//            )
//            OutlinedTextField(
//                label = {
//                    Text(
//                        text = "Password",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                },
//                maxLines = 1,
//                shape = RoundedCornerShape(32),
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp),
//                value = password.value,
//                onValueChange = {
//                    viewModel.onPasswordChange(it)
//                },
//            )
//            val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
//            Button(modifier = modifier
//                .fillMaxWidth()
//                .padding(top = 12.dp),
//                onClick = {
//                    localSoftwareKeyboardController?.hide()
//                    viewModel.onSignUp()
//                    coroutineScope.launch {
//                        snackBarHostState.showSnackbar(
//                            message = message,
//                            duration = SnackbarDuration.Long
//                        )
//                    }
//                }) {
//                Text("Sign up")
//            }
//        }
//    }
//}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateToSignInScreen: () -> Unit
) {
    val signUpUiState by viewModel.uiState.collectAsState()
    val isError = signUpUiState.signUpError != null
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AltakidTopAppBar(title = stringResource(id = AuthScreens.SignUp.title))
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
                    text = signUpUiState.signUpError ?: stringResource(id = R.string.unknown_error),
                    color = MaterialTheme.colorScheme.error
                )
            }
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(dimensionResource(id = R.dimen.padding_medium)),
//                value = signUpUiState.email,
//                onValueChange = viewModel::onEmailChange,
//                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
//                label = { Text(text = stringResource(id = R.string.email)) },
//                isError = isError,
//                maxLines = 1,
//                shape = RoundedCornerShape(32)
//            )
//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(dimensionResource(id = R.dimen.padding_medium)),
//                value = signUpUiState.password,
//                onValueChange = viewModel::onPasswordChange,
//                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
//                label = { Text(text = stringResource(id = R.string.password)) },
//                visualTransformation = PasswordVisualTransformation(),
//                isError = isError,
//                maxLines = 1,
//                shape = RoundedCornerShape(32)
//            )
            AuthForm(
                email = signUpUiState.email,
                password = signUpUiState.password,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                isError = isError
            )
            Text(
                text = stringResource(id = R.string.req_fields), modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(
                        start = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
            )
            Button(
                onClick = {
                    viewModel.onSignUp(context)
                    if (!isError) {
                        onNavigateToSignInScreen()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        dimensionResource(id = R.dimen.padding_medium)
                    )
            ) {
                Text(text = stringResource(id = R.string.sign_up_btn))
            }
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_medium)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.already_have_an_account))
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
                TextButton(onClick = onNavigateToSignInScreen) {
                    Text(text = stringResource(id = R.string.sign_in_btn))
                }
            }
            if (signUpUiState.isLoading) {
                CircularProgressIndicator()
            }
        }


        //launchedEffect to redirect to homepage if successfully signed up
    }
}

