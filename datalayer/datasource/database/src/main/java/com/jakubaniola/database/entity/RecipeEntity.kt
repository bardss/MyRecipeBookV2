package com.jakubaniola.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jakubaniola.model.Recipe

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val timeToPrepare: String,
    val rate: Int,
    val resultPhotoPath: String,
    val urlToRecipe: String,
    val ingredients: String,
    val recipe: String
)

fun RecipeEntity.toModel() = Recipe(
    id = id,
    name = name,
    timeToPrepare = timeToPrepare,
    rate = rate,
    resultPhotoPath = resultPhotoPath,
    urlToRecipe = urlToRecipe,
    ingredients = ingredients,
    recipe = recipe
)

fun Recipe.toEntity() = RecipeEntity(
    id = id,
    name = name,
    timeToPrepare = timeToPrepare,
    rate = rate,
    resultPhotoPath = resultPhotoPath,
    urlToRecipe = urlToRecipe,
    ingredients = ingredients,
    recipe = recipe
)
