package com.example.altakidtest.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.altakidtest.R

sealed class MainScreens(val route: String, @StringRes val titleResId: Int, @DrawableRes val iconResId: Int) {
    data object Profile: MainScreens("profile", R.string.profile, R.drawable.ic_profile)
    data object FamilyAccount: MainScreens("family_account", R.string.family_account, R.drawable.ic_family_account)
    data object Settings: MainScreens("settings", R.string.settings, R.drawable.ic_settings)
}