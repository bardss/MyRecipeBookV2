package com.jakubaniola.myrecipebook

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jakubaniola.addrecipe.navigation.navigateToAddRecipes
import com.jakubaniola.designsystem.components.CircularFloatingActionButton

@Composable
fun Fab(
    navController: NavHostController
) {
    navController.currentBackStackEntryAsState().value
    if (navController.isFabVisible()) {
        CircularFloatingActionButton(
            icon = navController.getFabIcon(),
            contentDescription = "Add new recipe button",
        ) {
            navController.navigateToAddRecipes()
        }
    }
}