package com.jakubaniola.addrecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.common.validateAndCopy
import com.jakubaniola.common.validation.ValidationType
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
            it.copy(name = validateAndCopy(name, ValidationType.EMPTY))
        }
    }

    fun onPrepTimeChange(prepTime: String) {
        updateAddingState {
            it.copy(prepTime = validateAndCopy(prepTime, ValidationType.EMPTY))
        }
    }

    fun onRateChange(rate: String) {
        updateAddingState {
            it.copy(rate = validateAndCopy(rate, ValidationType.EMPTY, ValidationType.NUMBER))
        }
    }

    fun onRecipeChange(recipe: String) {
        updateAddingState {
            it.copy(recipe = validateAndCopy(recipe))
        }
    }

    fun onLinkToRecipeChange(linkToRecipe: String) {
        updateAddingState {
            it.copy(linkToRecipe = validateAndCopy(linkToRecipe))
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
                    name = state.name.value,
                    timeToPrepare = state.prepTime.value,
                    rate = state.rate.value,
                    urlToRecipe = state.linkToRecipe.value,
                    recipe = state.recipe.value,
                    ingredients = "",
                    resultPhotoPath = ""
                )
                recipeRepository.saveRecipe(recipe)
                _uiState.value = UiState.OnAddSuccess
            }
        }
    }
}
