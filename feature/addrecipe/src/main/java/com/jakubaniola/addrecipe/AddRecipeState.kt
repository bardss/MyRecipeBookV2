package com.jakubaniola.addrecipe

import com.jakubaniola.common.FieldValue

data class AddRecipeState(
    val name: FieldValue = FieldValue(""),
    val prepTime: FieldValue = FieldValue(""),
    val rate: FieldValue = FieldValue(""),
    val recipe: FieldValue = FieldValue(""),
    val linkToRecipe: FieldValue = FieldValue(""),
)
