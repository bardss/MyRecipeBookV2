package com.jakubaniola.repository

import com.jakubaniola.database.dao.RecipeDao
import com.jakubaniola.database.entity.toEntity
import com.jakubaniola.database.entity.toModel
import com.jakubaniola.model.Recipe
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipesDao: RecipeDao
) {

    fun getRecipes() =
        recipesDao.getAllRecipes()
            .map { recipes -> recipes.map { recipe -> recipe.toModel() } }

    fun getRecipe(recipeId: Int) =
        recipesDao.getRecipe(recipeId)
            .map { recipe -> recipe.toModel() }

    suspend fun setRecipe(recipe: Recipe) =
        recipesDao.setRecipe(recipe.toEntity())
}
