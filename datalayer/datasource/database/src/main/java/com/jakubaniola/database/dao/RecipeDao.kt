package com.jakubaniola.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jakubaniola.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipeentity")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipeentity WHERE id IS :recipeId")
    fun getRecipe(recipeId: Int): Flow<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM recipeentity WHERE id IS :recipeId")
    suspend fun deleteRecipe(recipeId: Int)
}
