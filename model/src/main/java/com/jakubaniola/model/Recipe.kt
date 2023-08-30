package com.jakubaniola.model

data class Recipe(
    var id: Int? = null,
    val name: String,
    val timeToPrepare: String,
    val rate: Int,
    val urlToRecipe: String,
    val ingredients: List<String>,
    val recipe: String,
    val imageResultUri: String,
)
