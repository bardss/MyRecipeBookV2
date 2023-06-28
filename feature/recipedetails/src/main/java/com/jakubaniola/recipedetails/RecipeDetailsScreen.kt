package com.jakubaniola.recipedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.jakubaniola.designsystem.components.MrbScaffold
import com.jakubaniola.designsystem.components.fab.FabState
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme

@Composable
fun RecipeDetailsRoute(
    navigateToEditRecipe: (Int) -> Unit,
    navigateBack: () -> Unit,
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    RecipeDetailsScreen(
        navigateToEditRecipe = navigateToEditRecipe,
        navigateBack = navigateBack,
        onRemoveClick = viewModel::onRemoveClick,
        onConfirmRemoveClick = viewModel::onConfirmRemoveClick,
        onCancelRemoveClick = viewModel::onCancelRemoveClick,
        uiState = uiState,
        recipeId = viewModel.recipeId,
    )

}

@Composable
fun RecipeDetailsScreen(
    navigateToEditRecipe: (Int) -> Unit,
    navigateBack: () -> Unit,
    onRemoveClick: () -> Unit,
    onConfirmRemoveClick: () -> Unit,
    onCancelRemoveClick: () -> Unit,
    uiState: UiState,
    recipeId: Int
) {
    RecipeDetailsScaffold(
        navigateToEditRecipe = navigateToEditRecipe,
        onRemoveClick = onRemoveClick,
        onConfirmRemove = navigateBack,
        onConfirmRemoveClick = onConfirmRemoveClick,
        onCancelRemoveClick = onCancelRemoveClick,
        uiState = uiState,
        recipeId = recipeId,
    )
}

@Composable
fun RecipeDetailsScaffold(
    navigateToEditRecipe: (Int) -> Unit,
    onRemoveClick: () -> Unit,
    onConfirmRemove: () -> Unit,
    onConfirmRemoveClick: () -> Unit,
    onCancelRemoveClick: () -> Unit,
    uiState: UiState,
    recipeId: Int
) {
    MrbScaffold(
        topBarTitle = R.string.recipe,
        fabStates = listOf(
            FabState(
                icon = Icons.Default.Delete,
                contentDescription = "Edit recipe button",
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                onClick = onRemoveClick,
            ),
            FabState(
                icon = Icons.Default.Edit,
                contentDescription = "Edit recipe button",
                onClick = { navigateToEditRecipe(recipeId) },
            )
        ),
        content = {
            RecipeDetailsContent(it, uiState, onConfirmRemove)
        }
    )

    if (uiState is UiState.Details && uiState.isRemoveDialogVisible) {
        RemoveDialog(
            onConfirmRemoveClick,
            onCancelRemoveClick
        )
    }
}

@Composable
fun RecipeDetailsContent(
    paddingValues: PaddingValues,
    uiState: UiState,
    onConfirmRemove: () -> Unit,
) {
    val sidePadding = 18.dp
    val modifier = Modifier
        .padding(
            top = paddingValues.calculateTopPadding() + sidePadding,
            start = sidePadding,
            end = sidePadding
        )
        .fillMaxWidth()
    when (uiState) {
        is UiState.Details -> RecipeDetails(
            modifier = modifier,
            recipeDetails = uiState.state
        )

        is UiState.Loading -> Column(modifier = modifier) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(80.dp)
            )
        }

        is UiState.OnRemoveSuccess -> onConfirmRemove()
    }
}

@Composable
fun RemoveDialog(
    onConfirmRemoveClick: () -> Unit,
    onCancelRemoveClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = stringResource(id = R.string.are_you_sure_you_want_to_remove_recipe))
        },
        confirmButton = {
            Button(
                onClick = onConfirmRemoveClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            ) {
                Text(text = stringResource(id = R.string.remove))
            }
        },
        dismissButton = {
            Button(
                onClick = onCancelRemoveClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
    )
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

@Preview
@Composable
fun RecipeDetailsScaffoldPreview() {
    MyRecipeBookTheme {
        RecipeDetailsScaffold(
            { },
            { },
            { },
            { },
            { },
            UiState.Details(
                RecipeDetails(
                    name = "Recipe",
                    timeToPrepare = "5h",
                    rate = "5",
                    resultPhotoPath = "wfwaefwafe.pl",
                    urlToRecipe = "wefwr.przepisy.pl",
                    ingredients = "",
                    recipe = ""
                ),
                false
            ),
            1
        )
    }
}

@Preview
@Composable
fun RecipeDetailsScaffoldDialogPreview() {
    MyRecipeBookTheme {
        RecipeDetailsScaffold(
            { },
            { },
            { },
            { },
            { },
            UiState.Details(
                RecipeDetails(
                    name = "Recipe",
                    timeToPrepare = "5h",
                    rate = "5",
                    resultPhotoPath = "wfwaefwafe.pl",
                    urlToRecipe = "wefwr.przepisy.pl",
                    ingredients = "",
                    recipe = ""
                ),
                true
            ),
            1
        )
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
