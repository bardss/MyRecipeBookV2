package com.jakubaniola.myrecipebook

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jakubaniola.addrecipe.navigation.graphAddRecipes
import com.jakubaniola.recipeslist.recipeslist.navigation.graphRecipesListScreen
import com.jakubaniola.recipeslist.recipeslist.navigation.ROUTE_RECIPES_LIST

@Composable
fun MrbNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_RECIPES_LIST
    ) {
        graphRecipesListScreen(
            paddingValues = paddingValues
        )
        graphAddRecipes(
            paddingValues = paddingValues
        )
    }
}
