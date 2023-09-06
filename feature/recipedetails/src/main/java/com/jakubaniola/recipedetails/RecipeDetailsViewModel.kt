package com.jakubaniola.recipedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.common.di.DispatcherDefault
import com.jakubaniola.recipedetails.navigation.ARG_RECIPE_ID
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @DispatcherDefault private val dispatcher: CoroutineDispatcher,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    val recipeId: Int = checkNotNull(savedStateHandle[ARG_RECIPE_ID])
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        getLatestRecipeDetails()
    }

    private fun getLatestRecipeDetails() {
        viewModelScope.launch(dispatcher) {
            recipeRepository.getRecipe(recipeId)
                .map { it.toDetails() }
                .catch { }
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
        viewModelScope.launch(dispatcher) {
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
