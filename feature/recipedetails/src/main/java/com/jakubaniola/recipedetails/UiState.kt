package com.jakubaniola.recipedetails

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val state: RecipeDetails
    ) : UiState()
}
