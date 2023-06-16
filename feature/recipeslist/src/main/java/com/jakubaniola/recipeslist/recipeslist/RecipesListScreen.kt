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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jakubaniola.common.UiState
import com.jakubaniola.designsystem.components.CircularFloatingActionButton
import com.jakubaniola.designsystem.components.RecipeGridListItem
import com.jakubaniola.designsystem.components.RecipeSearchBar
import com.jakubaniola.designsystem.components.TopBar
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme
import com.jakubaniola.recipeslist.R

@ExperimentalMaterial3Api
@Composable
fun RecipesListScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipesListViewModel = hiltViewModel()
) {
    val uiState = viewModel.recipes.collectAsStateWithLifecycle().value
    Scaffold(
        topBar = {
            TopBar(stringResource(id = R.string.app_name))
        },
        floatingActionButton = {
            CircularFloatingActionButton(
                icon = Icons.Default.Add,
                contentDescription = "Add new recipe button"
            )
        },
        content = { paddingValues ->
            RecipesListContent(modifier, paddingValues, uiState)
        },
    )
}

@Composable
private fun RecipesListContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    uiState: UiState
) {
    Column(
        modifier = modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxWidth()
    ) {
        if (uiState is UiState.Success<*> && uiState.state is RecipesListState) {
            val uiState = uiState as UiState.Success<RecipesListState>
            if (!uiState.state.isRecipesListEmpty) {
                RecipeSearchBar { }
                RecipesGridList(uiState)
            } else {
                Text(
                    text = stringResource(id = R.string.no_recipes),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(80.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RecipesListScreenPreview() {
    MyRecipeBookTheme {
        RecipesListScreen()
    }
}

@Preview
@Composable
fun RecipesListContentPreview() {
    MyRecipeBookTheme {
        RecipesListContent(
            Modifier,
            PaddingValues(0.dp),
            UiState.Success(RecipesListState(listOf()))
        )
    }
}
