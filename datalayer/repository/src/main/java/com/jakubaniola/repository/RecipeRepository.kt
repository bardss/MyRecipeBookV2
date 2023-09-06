package com.jakubaniola.repository

import com.jakubaniola.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    fun getRecipe(recipeId: Int): Flow<Recipe>
    suspend fun removeRecipe(recipeId: Int)
    suspend fun setRecipe(recipe: Recipe)
}
