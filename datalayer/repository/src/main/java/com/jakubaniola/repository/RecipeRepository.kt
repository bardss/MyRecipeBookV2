package com.jakubaniola.repository

import com.jakubaniola.database.dao.RecipeDao
import com.jakubaniola.database.entity.toModel
import com.jakubaniola.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipesDao: RecipeDao
) {

    fun getRecipes(): Flow<List<Recipe>> =
        recipesDao.getAllRecipes()
            .map { recipes ->
                recipes.map { recipe -> recipe.toModel() }
            }
}
