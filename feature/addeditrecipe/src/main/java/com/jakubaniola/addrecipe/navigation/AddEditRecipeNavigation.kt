package com.jakubaniola.addrecipe.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jakubaniola.addrecipe.AddEditRecipeScreen
import com.jakubaniola.common.INVALID_ID

const val ARG_RECIPE_ID = "arg_recipe_id"
const val ROUTE_ADD_EDIT_RECIPES = "route_add_edit_recipes"

fun NavController.navigateToAddRecipes(navOptions: NavOptions? = null) {
    this.navigate(ROUTE_ADD_EDIT_RECIPES, navOptions)
}

fun NavController.navigateToEditRecipes(recipeId: Int) {
    this.navigate("$ROUTE_ADD_EDIT_RECIPES?$ARG_RECIPE_ID=$recipeId")
}

fun NavGraphBuilder.graphAddRecipes(
    onAddSuccess: () -> Unit
) {
    composable(
        route = "$ROUTE_ADD_EDIT_RECIPES?$ARG_RECIPE_ID={$ARG_RECIPE_ID}",
        arguments = listOf(
            navArgument(ARG_RECIPE_ID) {
                type = NavType.IntType
                defaultValue = INVALID_ID
            }
        )
    ) {
        AddEditRecipeScreen(onAddSuccess)
    }
}
