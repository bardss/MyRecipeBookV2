package com.jakubaniola.myrecipebook

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jakubaniola.addrecipe.navigation.graphAddRecipes
import com.jakubaniola.addrecipe.navigation.navigateToAddRecipes
import com.jakubaniola.addrecipe.navigation.navigateToEditRecipes
import com.jakubaniola.recipedetails.navigation.graphRecipeDetails
import com.jakubaniola.recipedetails.navigation.navigateToRecipeDetails
import com.jakubaniola.recipeslist.recipeslist.navigation.ROUTE_RECIPES_LIST
import com.jakubaniola.recipeslist.recipeslist.navigation.graphRecipesListScreen

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    navController.currentBackStackEntryAsState().value

    NavHost(
        navController = navController,
        startDestination = ROUTE_RECIPES_LIST
    ) {
        graphRecipesListScreen(
            navigateToAddRecipe = { navController.navigateToAddRecipes() },
            navigateToRecipeDetails = { navController.navigateToRecipeDetails(it) }
        )
        graphAddRecipes {
            navController.popBackStack()
        }
        graphRecipeDetails(
            navigateToEditRecipe = { navController.navigateToEditRecipes(it) },
            navigateBack = { navController.popBackStack() }
        )
    }
}
