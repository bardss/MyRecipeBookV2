package com.jakubaniola.repository

import com.jakubaniola.database.dao.RecipeLocal
import com.jakubaniola.database.entity.toEntity
import com.jakubaniola.database.entity.toModel
import com.jakubaniola.model.Recipe
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RecipeRepositoryImpl @Inject constructor(
    private val recipeLocal: RecipeLocal
) : RecipeRepository {

    override fun getRecipes() = recipeLocal.getAllRecipes()
        .map { recipes -> recipes.map { recipe -> recipe.toModel() } }

    override fun getRecipe(recipeId: Int) = recipeLocal.getRecipe(recipeId)
        .map { recipe -> recipe.toModel() }

    override suspend fun removeRecipe(recipeId: Int) =
        recipeLocal.deleteRecipe(recipeId)

    override suspend fun setRecipe(recipe: Recipe) =
        recipeLocal.setRecipe(recipe.toEntity())
}
