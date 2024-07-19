package com.example.altakidtest.navigation

import androidx.annotation.StringRes
import com.example.altakidtest.R

sealed class AuthScreens(val route: String, @StringRes val title: Int) {
    data object SignUp: AuthScreens("sign_up", R.string.sign_up_title)
    data object SignIn: AuthScreens("sign_in", R.string.sign_in_title)
    data object RestorePassword: AuthScreens("restore_password", R.string.restore_password_title)
}