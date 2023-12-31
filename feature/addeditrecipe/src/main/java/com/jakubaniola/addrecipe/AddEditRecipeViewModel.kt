package com.jakubaniola.addrecipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.addrecipe.navigation.ARG_RECIPE_ID
import com.jakubaniola.common.FieldValue
import com.jakubaniola.common.INVALID_ID
import com.jakubaniola.common.di.DispatcherDefault
import com.jakubaniola.common.validateAndCopy
import com.jakubaniola.common.validation.ValidationResult
import com.jakubaniola.common.validation.ValidationType
import com.jakubaniola.common.validation.guessUrlWhenNotValid
import com.jakubaniola.common.validation.validate
import com.jakubaniola.model.Recipe
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
    @DispatcherDefault private val dispatcher: CoroutineDispatcher,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val recipeId: Int = checkNotNull(savedStateHandle[ARG_RECIPE_ID])

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(dispatcher) {
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

    fun onIngredientChange(ingredient: String) {
        updateAddingState {
            it.copy(ingredient = validateAndCopy(ingredient, ValidationType.EMPTY))
        }
    }

    fun onLinkToRecipeChange(linkToRecipe: String) {
        updateAddingState {
            it.copy(urlToRecipe = validateAndCopy(linkToRecipe))
        }
    }

    fun onImageUpdate(imageUri: String?) {
        if (imageUri != null) {
            updateAddingState {
                it.copy(imageResultUri = imageUri)
            }
        }
    }

    fun onIngredientAddClick() {
        updateAddingState {
            val ingredient = it.ingredient
            if (!ingredient.isError() && ingredient.value.isNotEmpty()) {
                val updatedList = it.ingredients + ingredient.value
                it.copy(
                    ingredient = FieldValue(""),
                    ingredients = updatedList
                )
            } else {
                it
            }
        }
    }

    fun onIngredientRemoveClick(index: Int) {
        updateAddingState {
            val ingredients = it.ingredients
                .toMutableList()
            ingredients.removeAt(index)
            it.copy(ingredients = ingredients.toList())
        }
    }

    private fun updateAddingState(
        update: (AddEditRecipeState) -> AddEditRecipeState
    ) {
        viewModelScope.launch(dispatcher) {
            val value = _uiState.value
            if (value is UiState.AddEdit) {
                val updatedState = update(value.state)
                val updatedStateWithValidation = updatedState.copy(
                    isSaveEnabled = isRecipeDataValidToSave(updatedState)
                )
                _uiState.emit(UiState.AddEdit(updatedStateWithValidation))
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
        viewModelScope.launch(dispatcher) {
            val value = _uiState.value
            if (value is UiState.AddEdit) {
                val state = value.state
                val recipe = Recipe(
                    name = state.name.value,
                    timeToPrepare = state.prepTime.value,
                    rate = state.rate.value.toIntOrNull() ?: 0,
                    urlToRecipe = guessUrlWhenNotValid(state.urlToRecipe.value),
                    recipe = state.recipe.value,
                    ingredients = state.ingredients,
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
