package com.jakubaniola.addrecipe.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jakubaniola.addrecipe.AddRecipeScreen

const val ROUTE_ADD_RECIPES = "route_add_recipes"
const val FAB_VISIBILITY_ADD_RECIPE = true
val FAB_ICON_ADD_RECIPE = Icons.Default.Check

fun NavController.navigateToAddRecipes(navOptions: NavOptions? = null) {
    this.navigate(ROUTE_ADD_RECIPES, navOptions)
}

fun NavGraphBuilder.graphAddRecipes(
    paddingValues: PaddingValues
) {
    composable(route = ROUTE_ADD_RECIPES) {
        AddRecipeScreen(paddingValues)
    }
}
