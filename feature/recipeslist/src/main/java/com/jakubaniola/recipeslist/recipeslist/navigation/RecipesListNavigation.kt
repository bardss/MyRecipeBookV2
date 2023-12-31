package com.jakubaniola.recipeslist.recipeslist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jakubaniola.recipeslist.recipeslist.RecipesListRoute

const val ROUTE_RECIPES_LIST = "route_recipes_list"

fun NavGraphBuilder.graphRecipesListScreen(
    navigateToAddRecipe: () -> Unit,
    navigateToRecipeDetails: (Int) -> Unit
) {
    composable(route = ROUTE_RECIPES_LIST) {
        RecipesListRoute(
            navigateToAddRecipe = navigateToAddRecipe,
            navigateToRecipeDetails = navigateToRecipeDetails,
        )
    }
}
