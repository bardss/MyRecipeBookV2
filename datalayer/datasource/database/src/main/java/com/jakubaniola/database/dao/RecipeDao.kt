package com.jakubaniola.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jakubaniola.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipeentity")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipeentity WHERE id IS :recipeId")
    fun getRecipe(recipeId: Int): Flow<RecipeEntity>

    @Insert
    suspend fun addRecipe(recipe: RecipeEntity)

    @Update
    suspend fun editRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
}
