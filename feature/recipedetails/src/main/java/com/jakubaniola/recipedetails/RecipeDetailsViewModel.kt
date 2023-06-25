package com.jakubaniola.recipedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.recipedetails.navigation.ARG_RECIPE_ID
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    recipeRepository: RecipeRepository
) : ViewModel() {

    val recipeId: Int = checkNotNull(savedStateHandle[ARG_RECIPE_ID])

    private val _uiState = recipeRepository.getRecipe(recipeId)
        .map { recipe ->
            UiState.Success(recipe.toDetails())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading,
        )
    val uiState: StateFlow<UiState> = _uiState
}
