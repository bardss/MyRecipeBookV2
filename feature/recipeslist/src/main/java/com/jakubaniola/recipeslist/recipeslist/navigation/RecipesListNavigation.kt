package com.jakubaniola.recipeslist.recipeslist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jakubaniola.recipeslist.recipeslist.RecipesListScreen

const val ROUTE_RECIPES_LIST = "route_recipes_list"
const val FAB_VISIBILITY_RECIPES_LIST = true
val FAB_ICON_RECIPES_LIST = Icons.Default.Add

fun NavController.navigateToRecipesList(navOptions: NavOptions? = null) {
    this.navigate(ROUTE_RECIPES_LIST, navOptions)
}

fun NavGraphBuilder.graphRecipesListScreen(
    paddingValues: PaddingValues
) {
    composable(route = ROUTE_RECIPES_LIST) {
        RecipesListScreen(paddingValues = paddingValues)
    }
}

