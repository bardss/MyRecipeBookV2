package com.jakubaniola.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jakubaniola.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RecipeDao : RecipeLocal {
    @Query("SELECT * FROM recipeentity")
    override fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipeentity WHERE id IS :recipeId")
    override fun getRecipe(recipeId: Int): Flow<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun setRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM recipeentity WHERE id IS :recipeId")
    override suspend fun deleteRecipe(recipeId: Int)
}
