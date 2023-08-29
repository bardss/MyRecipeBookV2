package com.jakubaniola.addrecipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.addrecipe.navigation.ARG_RECIPE_ID
import com.jakubaniola.common.INVALID_ID
import com.jakubaniola.common.validateAndCopy
import com.jakubaniola.common.validation.ValidationResult
import com.jakubaniola.common.validation.ValidationType
import com.jakubaniola.common.validation.validate
import com.jakubaniola.model.Recipe
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditRecipeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val recipeId: Int = checkNotNull(savedStateHandle[ARG_RECIPE_ID])

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            recipeRepository.getRecipe(recipeId)
                .map { it.toAddEditRecipe() }
                .catch { emit(AddEditRecipeState()) }
                .takeWhile { _uiState.value is UiState.Loading }
                .collect {
                    _uiState.value = UiState.AddEdit(it)
                }
        }
    }

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

    fun onImageUpdate(imageUri: String?) {
        if (imageUri != null) {
            updateAddingState {
                it.copy(imageResultUri = imageUri)
            }
        }
    }

    private fun updateAddingState(
        update: (AddEditRecipeState) -> AddEditRecipeState
    ) {
        viewModelScope.launch {
            val value = _uiState.value
            if (value is UiState.AddEdit) {
                val updatedState = update(value.state)
                val updatedStateWithValidation = updatedState.copy(
                    isSaveEnabled = isRecipeDataValidToSave(updatedState)
                )
                _uiState.value = UiState.AddEdit(updatedStateWithValidation)
            }
        }
    }

    private fun isRecipeDataValidToSave(state: AddEditRecipeState): Boolean =
        listOf(
            validate(state.name.value, ValidationType.EMPTY),
            validate(state.prepTime.value, ValidationType.EMPTY),
            validate(state.rate.value, ValidationType.EMPTY, ValidationType.NUMBER),
        ).all { it == ValidationResult.VALID }

    fun onSaveClick() {
        viewModelScope.launch {
            val value = _uiState.value
            if (value is UiState.AddEdit) {
                val state = value.state
                val recipe = Recipe(
                    name = state.name.value,
                    timeToPrepare = state.prepTime.value,
                    rate = state.rate.value.toIntOrNull() ?: 0,
                    urlToRecipe = state.linkToRecipe.value,
                    recipe = state.recipe.value,
                    ingredients = "",
                    imageResultUri = state.imageResultUri
                )
                if (recipeId != INVALID_ID) {
                    recipe.id = recipeId
                }
                recipeRepository.setRecipe(recipe)
                _uiState.value = UiState.OnSaveSuccess
            }
        }
    }
}
