package com.jakubaniola.common

sealed class UiState {
    data class Error(val error: Throwable) : UiState()
    object Loading : UiState()
    data class Success<T>(
        val state: T
    ) : UiState()
}
