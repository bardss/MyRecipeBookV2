package com.jakubaniola.addrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.model.Recipe
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(
        UiState.Adding(AddRecipeState())
    )
    val uiState: StateFlow<UiState> = _uiState

    fun onNameChange(name: String) {
        updateAddingState {
            it.copy(name = name)
        }
    }

    fun onPrepTimeChange(prepTime: String) {
        updateAddingState {
            it.copy(prepTime = prepTime)
        }
    }

    fun onRateChange(rate: String) {
        updateAddingState {
            it.copy(rate = rate)
        }
    }

    fun onRecipeChange(recipe: String) {
        updateAddingState {
            it.copy(recipe = recipe)
        }
    }

    fun onLinkToRecipeChange(linkToRecipe: String) {
        updateAddingState {
            it.copy(linkToRecipe = linkToRecipe)
        }
    }

    private fun updateAddingState(
        update: (AddRecipeState) -> AddRecipeState
    ) {
        viewModelScope.launch {
            val value = _uiState.value
            if (value is UiState.Adding) {
                val updatedValue = update(value.state)
                _uiState.value = UiState.Adding(updatedValue)
            }
        }
    }

    fun onSaveClick() {
        viewModelScope.launch {
            val value = _uiState.value
            if (value is UiState.Adding) {
                val state = value.state
                val recipe = Recipe(
                    name = state.name,
                    timeToPrepare = state.prepTime,
                    rate = state.rate,
                    urlToRecipe = state.linkToRecipe,
                    recipe = state.recipe,
                    ingredients = "",
                    resultPhotoPath = ""
                )
                recipeRepository.saveRecipe(recipe)
                _uiState.value = UiState.OnAddSuccess
            }
        }
    }
}