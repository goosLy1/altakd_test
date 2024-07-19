package com.example.altakidtest.presentation.auth.signin

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altakidtest.R
import com.example.altakidtest.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    var currentUser by mutableStateOf<UserInfo?>(null)
        private set

    init {
        getCurrentUser()
    }

    fun getCurrentUser() = viewModelScope.launch {
        currentUser = authenticationRepository.getCurrentUser()
    }



    fun onEmailChange(email: String) {
        _uiState.update { currentState -> currentState.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState -> currentState.copy(password = password) }
    }

    private fun validateSignInForm() =
        _uiState.value.email.isNotBlank() && _uiState.value.password.isNotBlank()

//    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//        _uiState.update { currentState -> currentState.copy(signInError = throwable.message) }
//    }

    fun onSignIn(context: Context) = viewModelScope.launch() {
        try {
            if (!validateSignInForm()) {
                throw IllegalArgumentException(context.getString(R.string.sign_up_form_empty_fields))
            }
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    signInError = null
                )
            }
            val result = authenticationRepository.signIn(
                email = _uiState.value.email,
                password = _uiState.value.password
            )
            if (result) {
                Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, context.getString(R.string.failure), Toast.LENGTH_SHORT)
                    .show()
                _uiState.update { currentState -> currentState.copy(signInError = context.getString(R.string.unknown_error)) }
            }

        } catch (e: Exception) {
            _uiState.update { currentState -> currentState.copy(signInError = e.message) }
//            e.printStackTrace()
        } finally {
            _uiState.update { currentState -> currentState.copy(isLoading = false) }
        }
    }

    fun onSignOut() = viewModelScope.launch {
        try {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    signInError = null
                )
            }
            authenticationRepository.signOut()
        } catch (e: Exception) {
            _uiState.update { currentState -> currentState.copy(signInError = e.localizedMessage) }
            e.printStackTrace()
        } finally {
            _uiState.update { currentState -> currentState.copy(isLoading = false) }
        }
    }
    fun onResetPassword(email: String) = viewModelScope.launch {
        authenticationRepository.resetPassword(email)
    }
}

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val signInError: String? = null
)