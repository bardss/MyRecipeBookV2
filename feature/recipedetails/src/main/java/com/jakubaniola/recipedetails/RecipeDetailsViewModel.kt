package com.jakubaniola.recipedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.recipedetails.navigation.ARG_RECIPE_ID
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    val recipeId: Int = checkNotNull(savedStateHandle[ARG_RECIPE_ID])
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        getLatestRecipeDetails()
    }

    private fun getLatestRecipeDetails() {
        viewModelScope.launch {
            recipeRepository.getRecipe(recipeId)
                .map { it.toDetails() }
                .collect {
                    _uiState.value = UiState.Details(it)
                }
        }
    }

    fun onRemoveClick() {
        val state = _uiState.value
        if (state is UiState.Details) {
            _uiState.value = state.copy(
                isRemoveDialogVisible = true
            )
        }
    }

    fun onConfirmRemoveClick() {
        viewModelScope.launch {
            recipeRepository.removeRecipe(recipeId)
            _uiState.value = UiState.OnRemoveSuccess
        }
    }

    fun onCancelRemoveClick() {
        val state = _uiState.value
        if (state is UiState.Details) {
            _uiState.value = state.copy(
                isRemoveDialogVisible = false
            )
        }
    }
}
