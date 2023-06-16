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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.common.UiState
import com.jakubaniola.designsystem.components.ListEmptyState
import com.jakubaniola.designsystem.components.RecipeGridListItem
import com.jakubaniola.designsystem.components.RecipeSearchBar
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme
import com.jakubaniola.recipeslist.R

@Composable
fun RecipesListScreen(
    paddingValues: PaddingValues,
    viewModel: RecipesListViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxWidth()
    ) {
        val uiState = viewModel.recipes.collectAsStateWithLifecycle().value
        if (uiState is UiState.Success<*> && uiState.state is RecipesListState) {
            val uiState = uiState as UiState.Success<RecipesListState>
            if (!uiState.state.isRecipesListEmpty) {
                RecipeSearchBar { }
                RecipesGridList(uiState)
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
private fun RecipesGridList(uiState: UiState.Success<RecipesListState>) {
    val recipes = uiState.state.recipes
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
        RecipesListScreen(
            PaddingValues(0.dp)
        )
    }
}
