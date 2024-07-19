package com.example.altakidtest.common

sealed class ScreenViewState<out T> {
    object Loading: ScreenViewState<Nothing>()
    data class Success<T>(val data: T): ScreenViewState<T>()
    data class Error<T>(val message: String?): ScreenViewState<Nothing>()
}