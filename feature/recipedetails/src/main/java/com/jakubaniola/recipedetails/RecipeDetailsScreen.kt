package com.jakubaniola.recipedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.common.R
import com.jakubaniola.designsystem.components.FabState
import com.jakubaniola.designsystem.components.ListEmptyState
import com.jakubaniola.designsystem.components.MrbScaffold
import com.jakubaniola.designsystem.components.RecipeSearchBar
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme

@Composable
fun RecipeDetailsScreen(
//    navigateToEditRecipe: () -> Unit,
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
) {
    MrbScaffold(
        topBarTitle = R.string.recipe,
        fabState = FabState(
            icon = Icons.Default.Edit,
            contentDescription = "Edit recipe button",
            onClick = { },
        ),
        content = {
            RecipeDetailsContent(it, viewModel)
        }
    )
}

@Composable
fun RecipeDetailsContent(
    paddingValues: PaddingValues,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxWidth()
    ) {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        if (uiState is UiState.Success) {
            Text(
                text = uiState.state.name
            )
            Text(
                text = uiState.state.recipe
            )
            Text(
                text = uiState.state.rate
            )
            Text(
                text = uiState.state.timeToPrepare
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(80.dp)
            )
        }
    }
}

@Preview
@Composable
fun RecipeDetailsScreenPreview() {
    MyRecipeBookTheme {
        RecipeDetailsScreen()
    }
}