package com.jakubaniola.recipeslist.recipeslist

data class RecipesListState(
    val recipes: List<RecipeItem>
)

data class RecipeItem(
    val name: String,
    val rateResource: Int,
    val rateValue: String,
    val prepTimeResource: Int,
    val prepTimeValue: String,
    val image: String
)
