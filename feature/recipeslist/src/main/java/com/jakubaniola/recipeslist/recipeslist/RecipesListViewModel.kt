package com.jakubaniola.recipeslist.recipeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.common.di.IoDispatcher
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch(dispatcher) {
            recipeRepository.getRecipes()
                .map { recipes ->
                    recipes.map { recipe -> recipe.toItem() }
                }
                .map { recipeItems ->
                    UiState.Success(
                        RecipesListState(
                            allRecipes = recipeItems,
                            filteredRecipes = recipeItems,
                            query = "",
                            isRecipesEmptyStateVisible = recipeItems.isEmpty(),
                            isSearchEmptyStateVisible = false,
                            isSearchBarVisible = recipeItems.isNotEmpty()
                        )
                    )
                }
                .collect {
                    _uiState.value = it
                }
        }
    }

    fun updateQuery(typed: String) {
        val value = _uiState.value
        if (value is UiState.Success) {
            viewModelScope.launch {
                filter(
                    uiState = value,
                    typed = typed
                )
            }
        }
    }

    fun onOrderByRateClick() {
        val value = _uiState.value
        if (value is UiState.Success) {
            viewModelScope.launch {
                filter(
                    uiState = value,
                    orderByRate = !value.state.isOrderedByRate
                )
            }
        }
    }

    private fun filter(
        uiState: UiState.Success,
        typed: String = uiState.state.query,
        orderByRate: Boolean = uiState.state.isOrderedByRate
    ) {
        val state = uiState.state
        val filtered = state.allRecipes.filter(
            query = typed,
            orderByRate = orderByRate
        )
        val newUiState = state.copy(
            filteredRecipes = filtered,
            query = typed,
            isSearchEmptyStateVisible = filtered.isEmpty(),
            isOrderedByRate = orderByRate
        )
        _uiState.value = uiState.copy(state = newUiState)
    }
}
