package com.jakubaniola.addrecipe

sealed class UiState {
    data class AddEdit(
        val state: AddEditRecipeState
    ) : UiState()
    object OnSaveSuccess : UiState()
    object Loading : UiState()
}
