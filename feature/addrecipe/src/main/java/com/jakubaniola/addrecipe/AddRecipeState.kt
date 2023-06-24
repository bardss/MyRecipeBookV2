package com.jakubaniola.addrecipe

data class AddRecipeState(
    val name: String = "",
    val prepTime: String = "",
    val rate: String = "",
    val recipe: String = "",
    val linkToRecipe: String = "",
)