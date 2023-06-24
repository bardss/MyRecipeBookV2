package com.jakubaniola.recipeslist.recipeslist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jakubaniola.recipeslist.recipeslist.RecipesListScreen

const val ROUTE_RECIPES_LIST = "route_recipes_list"

fun NavGraphBuilder.graphRecipesListScreen(
    navigateToAddRecipe: () -> Unit
) {
    composable(route = ROUTE_RECIPES_LIST) {
        RecipesListScreen(navigateToAddRecipe)
    }
}
