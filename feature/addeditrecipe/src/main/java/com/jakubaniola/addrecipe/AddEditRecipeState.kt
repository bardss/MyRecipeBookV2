package com.jakubaniola.addrecipe

import com.jakubaniola.common.FieldValue
import com.jakubaniola.model.Recipe

data class AddEditRecipeState(
    val name: FieldValue = FieldValue(""),
    val prepTime: FieldValue = FieldValue(""),
    val rate: FieldValue = FieldValue(""),
    val recipe: FieldValue = FieldValue(""),
    val linkToRecipe: FieldValue = FieldValue(""),
    val isSaveEnabled: Boolean = false
)

fun Recipe.toAddEditRecipe() = AddEditRecipeState(
    name = FieldValue(name),
    prepTime = FieldValue(timeToPrepare),
    rate = FieldValue(rate),
    recipe = FieldValue(recipe),
    linkToRecipe = FieldValue(urlToRecipe),
    isSaveEnabled = true
)