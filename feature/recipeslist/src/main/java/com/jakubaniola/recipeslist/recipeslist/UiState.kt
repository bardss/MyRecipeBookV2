package com.jakubaniola.recipeslist.recipeslist

sealed class UiState {
    object Loading : UiState()
    data class Success(
        val state: RecipesListState
    ) : UiState()
}