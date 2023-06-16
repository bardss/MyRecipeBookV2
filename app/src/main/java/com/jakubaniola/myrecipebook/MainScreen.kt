package com.jakubaniola.myrecipebook

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jakubaniola.addrecipe.navigation.navigateToAddRecipes
import com.jakubaniola.designsystem.components.CircularFloatingActionButton
import com.jakubaniola.designsystem.components.TopBar
import com.jakubaniola.recipeslist.R

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopBar(stringResource(id = R.string.app_name))
        },
        floatingActionButton = {
            Fab(navController)
        },
        content = { paddingValues ->
            MrbNavHost(
                paddingValues,
                navController
            )
        },
    )
}
