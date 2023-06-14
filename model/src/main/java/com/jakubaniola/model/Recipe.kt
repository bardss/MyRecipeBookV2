package com.jakubaniola.model

data class Recipe(
    val id: Int? = null,
    val name: String,
    val timeToPrepare: String,
    val rate: Int,
    val resultPhotoPath: String,
    val urlToRecipe: String,
    val ingredients: String,
    val recipe: String
)
