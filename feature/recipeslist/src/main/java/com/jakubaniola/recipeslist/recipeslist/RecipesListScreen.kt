package com.jakubaniola.recipeslist.recipeslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.designsystem.components.ListEmptyState
import com.jakubaniola.designsystem.components.MrbScaffold
import com.jakubaniola.designsystem.components.RecipeGridListItem
import com.jakubaniola.designsystem.components.RecipeSearchBar
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme
import com.jakubaniola.recipeslist.R

@Composable
fun RecipesListScreen(
    navigateToAddRecipe: () -> Unit,
    viewModel: RecipesListViewModel = hiltViewModel(),
) {
    MrbScaffold(
        topBarTitle = R.string.app_name,
        fabIcon = Icons.Default.Add,
        fabContentDescription = "Add new recipe button",
        onFabClick = navigateToAddRecipe,
        content = {
            RecipesListContent(it, viewModel)
        }
    )
}

@Composable
fun RecipesListContent(
    paddingValues: PaddingValues,
    viewModel: RecipesListViewModel
) {
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxWidth()
    ) {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        if (uiState is UiState.Success) {
            if (!uiState.state.isRecipesListEmpty) {
                RecipeSearchBar { }
                RecipesGridList(uiState.state.recipes)
            } else {
                ListEmptyState(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    resourceText = R.string.no_recipes
                )
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(80.dp)
            )
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun RecipesGridList(recipes: List<RecipeItem>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            top = 8.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 120.dp
        ),
        content = {
            items(recipes) { recipe ->
                RecipeGridListItem(
                    name = recipe.name,
                    rate = recipe.rateValue,
                    prepTime = recipe.prepTimeValue,
                    image = recipe.image,
                    onGridListItemClick = {}
                )
            }
        }
    )
}

@Preview
@Composable
fun RecipesListContentPreview() {
    MyRecipeBookTheme {
        RecipesListScreen({})
    }
}
