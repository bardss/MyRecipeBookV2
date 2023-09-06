package com.jakubaniola.database.dao

import com.jakubaniola.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeLocal {
    fun getAllRecipes(): Flow<List<RecipeEntity>>
    fun getRecipe(recipeId: Int): Flow<RecipeEntity>
    suspend fun setRecipe(recipe: RecipeEntity)
    suspend fun deleteRecipe(recipeId: Int)
}
