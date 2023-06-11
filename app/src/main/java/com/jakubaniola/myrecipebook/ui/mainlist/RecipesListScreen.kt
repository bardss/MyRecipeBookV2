package com.jakubaniola.myrecipebook.ui.mainlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jakubaniola.components.RecipeSearchBar
import com.jakubaniola.components.TopBar
import com.jakubaniola.myrecipebook.R
import com.jakubaniola.myrecipebook.ui.theme.MyRecipeBookTheme

@ExperimentalMaterial3Api
@Composable
fun RecipesListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(stringResource(id = R.string.app_name))
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = MaterialTheme.shapes.extraLarge,
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = ""
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .padding(
                        top = paddingValues.calculateTopPadding()
                    )
            ) {
                RecipeSearchBar {
                }
                Text(
                    text = "Future list",

                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RecipesListScreenPreview() {
    MyRecipeBookTheme {
        RecipesListScreen()
    }
}
