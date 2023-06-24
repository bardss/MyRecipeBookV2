package com.jakubaniola.addrecipe.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jakubaniola.addrecipe.AddRecipeScreen

const val ROUTE_ADD_RECIPES = "route_add_recipes"

fun NavController.navigateToAddRecipes(navOptions: NavOptions? = null) {
    this.navigate(ROUTE_ADD_RECIPES, navOptions)
}

fun NavGraphBuilder.graphAddRecipes(
    onAddSuccess: () -> Unit
) {
    composable(route = ROUTE_ADD_RECIPES) {
        AddRecipeScreen(onAddSuccess)
    }
}
