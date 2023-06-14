package com.jakubaniola.common

sealed class UiState<T> {
    data class Error(val error: Throwable) : UiState<Throwable>()
    object Loading : UiState<Nothing>()
    data class Success<T>(
        val state: T
    ) : UiState<T>()
}