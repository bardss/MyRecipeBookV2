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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.common.R
import com.jakubaniola.designsystem.components.ListEmptyState
import com.jakubaniola.designsystem.components.MrbScaffold
import com.jakubaniola.designsystem.components.RecipeGridListItem
import com.jakubaniola.designsystem.components.RecipeSearchBar
import com.jakubaniola.designsystem.components.fab.FabState
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme

@Composable
fun RecipesListRoute(
    navigateToAddRecipe: () -> Unit,
    navigateToRecipeDetails: (Int) -> Unit,
    viewModel: RecipesListViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    RecipesListScreen(
        navigateToAddRecipe = navigateToAddRecipe,
        navigateToRecipeDetails = navigateToRecipeDetails,
        onSearchQuery = viewModel::updateQuery,
        uiState = uiState,
    )
}

@Composable
fun RecipesListScreen(
    navigateToAddRecipe: () -> Unit,
    navigateToRecipeDetails: (Int) -> Unit,
    onSearchQuery: (String) -> Unit,
    uiState: UiState
) {
    MrbScaffold(
        topBarTitle = R.string.app_name,
        fabStates = listOf(
            FabState(
                icon = Icons.Default.Add,
                contentDescription = "Add new recipe button",
                onClick = navigateToAddRecipe,
            )
        ),
        content = {
            RecipesListContent(it, navigateToRecipeDetails, onSearchQuery, uiState)
        }
    )
}


@Composable
fun RecipesListContent(
    paddingValues: PaddingValues,
    navigateToRecipeDetails: (Int) -> Unit,
    onSearchQuery: (String) -> Unit,
    uiState: UiState
) {
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxWidth()
    ) {
        if (uiState is UiState.Success) {
            if (uiState.state.isSearchBarVisible) {
                RecipeSearchBar(uiState.state.query, onSearchQuery)
            }
            if (uiState.state.isRecipesListEmpty) {
                ListEmptyState(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    resourceText = R.string.no_recipes
                )
            } else if (uiState.state.isSearchResultEmpty) {
                ListEmptyState(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    resourceText = R.string.no_recipes_found
                )
            } else {
                RecipesGridList(uiState.state.filteredRecipes, navigateToRecipeDetails)
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
private fun RecipesGridList(
    recipes: List<RecipeItem>,
    navigateToRecipeDetails: (Int) -> Unit,
) {
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
                    rateLabel = stringResource(id = R.string.rate_with_colon),
                    rate = recipe.rateValue,
                    prepTimeLabel = stringResource(id = R.string.prep_time_with_colon),
                    prepTime = recipe.prepTimeValue,
                    image = recipe.image,
                    onGridListItemClick = {
                        navigateToRecipeDetails(recipe.id)
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun RecipesListContentPreview() {
    MyRecipeBookTheme {
        RecipesListRoute(
            {},
            {}
        )
    }
}
