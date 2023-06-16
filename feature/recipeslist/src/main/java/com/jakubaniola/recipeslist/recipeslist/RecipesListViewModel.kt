package com.jakubaniola.recipeslist.recipeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.common.UiState
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipes = recipeRepository.getRecipes()
        .map { recipes ->
            recipes.map { recipe -> recipe.toItem() }
        }
        .map { recipeItems ->
            UiState.Success(
                RecipesListState(
                    recipes = recipeItems,
                    isRecipesListEmpty = recipeItems.isEmpty()
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading,
        )
    val recipes: StateFlow<UiState> = _recipes
}
