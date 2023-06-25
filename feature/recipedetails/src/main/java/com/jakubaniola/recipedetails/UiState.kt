package com.jakubaniola.recipedetails

sealed class UiState {
    object Loading : UiState()
    object OnRemoveSuccess : UiState()
    data class Details(
        val state: RecipeDetails,
        val isRemoveDialogVisible: Boolean = false,
    ) : UiState()
}
