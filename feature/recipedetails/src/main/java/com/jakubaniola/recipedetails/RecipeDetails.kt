package com.jakubaniola.recipedetails

import com.jakubaniola.model.Recipe

data class RecipeDetails(
    val name: String,
    val timeToPrepare: String,
    val rate: String,
    val resultPhotoPath: String,
    val urlToRecipe: String,
    val ingredients: String,
    val recipe: String
)

fun Recipe.toDetails() = RecipeDetails(
    name = name,
    timeToPrepare = timeToPrepare,
    rate = rate,
    resultPhotoPath = resultPhotoPath,
    urlToRecipe = urlToRecipe,
    ingredients = ingredients,
    recipe = recipe
)
