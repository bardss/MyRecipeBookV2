package com.jakubaniola.recipeslist.recipeslist

import com.jakubaniola.model.Recipe
import com.jakubaniola.recipeslist.R

data class RecipesListState(
    val recipes: List<RecipeItem>,
    val isRecipesListEmpty: Boolean = false,
    val isSearchResultEmpty: Boolean = false
)

data class RecipeItem(
    val name: String,
    val rateResource: Int,
    val rateValue: String,
    val prepTimeResource: Int,
    val prepTimeValue: String,
    val image: String
)

fun Recipe.toItem() = RecipeItem(
    name = name,
    rateResource = R.string.rate,
    rateValue = rate.toString(),
    prepTimeResource = R.string.prep_time,
    prepTimeValue = timeToPrepare,
    image = urlToRecipe
)