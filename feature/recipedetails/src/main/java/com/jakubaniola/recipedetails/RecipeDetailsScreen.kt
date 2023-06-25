package com.jakubaniola.recipedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.common.R
import com.jakubaniola.designsystem.components.FabState
import com.jakubaniola.designsystem.components.MrbScaffold
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme

@Composable
fun RecipeDetailsScreen(
    navigateToEditRecipe: (Int) -> Unit,
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    MrbScaffold(
        topBarTitle = R.string.recipe,
        fabState = FabState(
            icon = Icons.Default.Edit,
            contentDescription = "Edit recipe button",
            onClick = { navigateToEditRecipe(viewModel.recipeId) },
        ),
        content = {
            RecipeDetailsContent(it, uiState)
        }
    )
}

@Composable
fun RecipeDetailsContent(
    paddingValues: PaddingValues,
    uiState: UiState
) {
    val sidePadding = 18.dp
    val modifier = Modifier
        .padding(
            top = paddingValues.calculateTopPadding() + sidePadding,
            start = sidePadding,
            end = sidePadding
        )
        .fillMaxWidth()
    if (uiState is UiState.Success) {
        RecipeDetails(
            modifier = modifier,
            recipeDetails = uiState.state
        )
    } else {
        Column(modifier = modifier) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(80.dp)
            )
        }
    }
}

@Composable
fun RecipeDetails(
    modifier: Modifier,
    recipeDetails: RecipeDetails
) {
    Column(modifier = modifier) {
        Text(
            text = recipeDetails.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .padding(
                    top = 18.dp
                )
        ) {
            val startValuePadding = 16.dp
            Text(
                text = stringResource(id = R.string.prep_time_with_colon),
            )
            Text(
                text = recipeDetails.timeToPrepare,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = startValuePadding
                    )
            )
            Text(
                text = stringResource(id = R.string.rate_with_colon),
            )
            Text(
                text = recipeDetails.rate,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = startValuePadding
                    )
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun RecipeDetailsPreview() {
    MyRecipeBookTheme {
        RecipeDetails(
            Modifier,
            RecipeDetails(
                name = "Spaghetti Bolognese",
                timeToPrepare = "4h",
                rate = "9",
                resultPhotoPath = "https://awfewef.pl",
                urlToRecipe = "www.przepisy.pl",
                ingredients = "Ingredients",
                recipe = "Recipe"
            )
        )
    }
}
