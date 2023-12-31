package com.jakubaniola.recipedetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jakubaniola.recipedetails.RecipeDetailsRoute

const val ARG_RECIPE_ID = "arg_recipe_id"
const val ROUTE_RECIPE_DETAIL = "route_recipe_detail"

fun NavController.navigateToRecipeDetails(recipeId: Int) {
    this.navigate(
        "$ROUTE_RECIPE_DETAIL/$recipeId"
    )
}

fun NavGraphBuilder.graphRecipeDetails(
    navigateToEditRecipe: (Int) -> Unit,
    navigateBack: () -> Unit
) {
    composable(
        route = "$ROUTE_RECIPE_DETAIL/{$ARG_RECIPE_ID}",
        arguments = listOf(
            navArgument(ARG_RECIPE_ID) {
                type = NavType.IntType
            }
        )
    ) {
        RecipeDetailsRoute(
            navigateToEditRecipe = navigateToEditRecipe,
            navigateBack = navigateBack
        )
    }
}
