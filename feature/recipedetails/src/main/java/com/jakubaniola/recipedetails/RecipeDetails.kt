package com.jakubaniola.recipedetails

import com.jakubaniola.model.Recipe

data class RecipeDetails(
    val name: String,
    val timeToPrepare: String,
    val rate: String,
    val urlToRecipe: String,
    val ingredients: String,
    val recipe: String,
    val imageResultUri: String,
)

fun Recipe.toDetails() = RecipeDetails(
    name = name,
    timeToPrepare = timeToPrepare,
    rate = rate.toString(),
    urlToRecipe = urlToRecipe,
    ingredients = ingredients,
    recipe = recipe,
    imageResultUri = imageResultUri
)
