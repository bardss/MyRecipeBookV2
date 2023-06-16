package com.jakubaniola.myrecipebook

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.navigation.NavHostController
import com.jakubaniola.addrecipe.navigation.FAB_ICON_ADD_RECIPE
import com.jakubaniola.addrecipe.navigation.FAB_VISIBILITY_ADD_RECIPE
import com.jakubaniola.addrecipe.navigation.ROUTE_ADD_RECIPES
import com.jakubaniola.recipeslist.recipeslist.navigation.FAB_ICON_RECIPES_LIST
import com.jakubaniola.recipeslist.recipeslist.navigation.FAB_VISIBILITY_RECIPES_LIST
import com.jakubaniola.recipeslist.recipeslist.navigation.ROUTE_RECIPES_LIST

fun NavHostController.isFabVisible() = when (this.currentDestination?.route) {
    ROUTE_ADD_RECIPES -> FAB_VISIBILITY_ADD_RECIPE
    ROUTE_RECIPES_LIST -> FAB_VISIBILITY_RECIPES_LIST
    else -> false
}

fun NavHostController.getFabIcon() = when (this.currentDestination?.route) {
    ROUTE_ADD_RECIPES -> FAB_ICON_ADD_RECIPE
    ROUTE_RECIPES_LIST -> FAB_ICON_RECIPES_LIST
    else -> Icons.Default.Check
}