package com.jakubaniola.recipeslist.recipeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.common.DEBOUNCE
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    recipeRepository: RecipeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
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
                            isRecipesListEmpty = recipeItems.isEmpty(),
                            isSearchResultEmpty = false
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
                val state = value.state
                val filtered = state.allRecipes.filter(typed)
                val newUiState = state.copy(
                    filteredRecipes = filtered,
                    query = typed,
                    isSearchResultEmpty = filtered.isEmpty(),
                )
                _uiState.value = value.copy(newUiState)
            }
        }
    }
}
