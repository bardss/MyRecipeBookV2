package com.jakubaniola.addrecipe

sealed class UiState {
    data class Adding(
        val state: AddRecipeState
    ) : UiState()
    object OnAddSuccess : UiState()
}
