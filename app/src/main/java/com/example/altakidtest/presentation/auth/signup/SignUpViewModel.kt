package com.example.altakidtest.presentation.auth.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altakidtest.R
import com.example.altakidtest.data.dto.ProfileDto
import com.example.altakidtest.data.repository.AuthenticationRepository
import com.example.altakidtest.data.repository.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {
//    private val _email = MutableStateFlow("")
//    val email: Flow<String> = _email.asStateFlow()
//
//    private val _password = MutableStateFlow("")
//    val password: Flow<String> = _password.asStateFlow()
//
//    private val _message = MutableStateFlow("")
//    val message: Flow<String> = _message.asStateFlow()
//
//    fun onEmailChange(email: String) {
//        _email.value = email
//    }
//
//    fun onPasswordChange(password: String) {
//        _password.value = password
//    }
//
//    fun onSignUp() {
//        viewModelScope.launch {
//            val result = signUpUseCase.execute(
//                SignUpUseCase.Input(
//                    email = _email.value,
//                    password = _password.value
//                )
//            )
//            when (result) {
//                is SignUpUseCase.Output.Success -> {
//                    _message.value = "Account created successfully!"
//                }
//                else -> {
//                    _message.value = "Create account failed !"
//
//                }
//            }
//        }
//    }

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { currentState -> currentState.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState -> currentState.copy(password = password) }
    }

    private fun validateSignUpForm() =
        _uiState.value.email.isNotBlank() && _uiState.value.password.isNotBlank()

    fun onSignUp(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignUpForm()) {
                throw IllegalArgumentException(context.getString(R.string.sign_up_form_empty_fields))
            }
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    signUpError = null
                )
            }
            val result = authenticationRepository.signUp(
                email = _uiState.value.email,
                password = _uiState.value.password
            )
//            if (result) {
//                Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                Toast.makeText(context, context.getString(R.string.failure), Toast.LENGTH_SHORT)
//                    .show()
//            }
            val profile = ProfileDto(
                userId = result?.id,
                email = result?.email,
                isParent = true
            )
            userProfileRepository.createUserProfile(profile)
        } catch (e: Exception) {
            _uiState.update { currentState -> currentState.copy(signUpError = e.localizedMessage) }
            e.printStackTrace()
        } finally {
            _uiState.update { currentState -> currentState.copy(isLoading = false) }
        }
    }

}

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val signUpError: String? = null
)