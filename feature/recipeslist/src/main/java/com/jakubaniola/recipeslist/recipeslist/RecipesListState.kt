package com.jakubaniola.recipeslist.recipeslist

import com.jakubaniola.common.R
import com.jakubaniola.model.Recipe

data class RecipesListState(
    val allRecipes: List<RecipeItem>,
    val filteredRecipes: List<RecipeItem>,
    val query: String = "",
    val isRecipesListEmpty: Boolean = false,
    val isSearchResultEmpty: Boolean = false,
    val isSearchBarVisible: Boolean = false
)

data class RecipeItem(
    val id: Int,
    val name: String,
    val rateResource: Int,
    val rateValue: String,
    val prepTimeResource: Int,
    val prepTimeValue: String,
    val image: String
)

fun Recipe.toItem() = RecipeItem(
    id = id ?: 0,
    name = name,
    rateResource = R.string.rate_with_colon,
    rateValue = rate,
    prepTimeResource = R.string.prep_time_with_colon,
    prepTimeValue = timeToPrepare,
    image = urlToRecipe
)
