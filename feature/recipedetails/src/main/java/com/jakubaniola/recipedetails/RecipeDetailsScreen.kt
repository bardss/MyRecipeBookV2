package com.jakubaniola.recipedetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.URLUtil
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.common.R
import com.jakubaniola.common.navigateToUrl
import com.jakubaniola.designsystem.components.ImagePreview
import com.jakubaniola.designsystem.components.ListRow
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
    MrbScaffold(topBarTitle = R.string.recipe, fabStates = listOf(
        FabState(
            icon = Icons.Default.Delete,
            contentDescription = "Edit recipe button",
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer,
            onClick = onRemoveClick,
        ), FabState(
            icon = Icons.Default.Edit,
            contentDescription = "Edit recipe button",
            onClick = { navigateToEditRecipe(recipeId) },
        )
    ), content = {
        RecipeDetailsContent(it, uiState, onConfirmRemove)
    })

    if (uiState is UiState.Details && uiState.isRemoveDialogVisible) {
        RemoveDialog(
            onConfirmRemoveClick, onCancelRemoveClick
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
            top = paddingValues.calculateTopPadding(),
            start = sidePadding,
            end = sidePadding
        )
        .fillMaxWidth()
    when (uiState) {
        is UiState.Details -> RecipeDetails(
            modifier = modifier, recipeDetails = uiState.state
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
                onClick = onConfirmRemoveClick, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            ) {
                Text(text = stringResource(id = R.string.remove))
            }
        },
        dismissButton = {
            Button(
                onClick = onCancelRemoveClick, colors = ButtonDefaults.buttonColors(
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
    LazyColumn(
        modifier = modifier
    ) {
        val labelModifier = Modifier.padding(0.dp, 16.dp, 0.dp, 8.dp)
        val valueModifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)

        item {
            if (recipeDetails.imageResultUri.isNotEmpty()) {
                ImagePreview(imageUri = recipeDetails.imageResultUri)
            }

            Text(
                text = recipeDetails.name,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.padding(
                    top = 18.dp
                )
            ) {
                val startValuePadding = 16.dp
                Text(
                    text = stringResource(id = R.string.prep_time_with_colon),
                    fontWeight = FontWeight.Light,
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
                    fontWeight = FontWeight.Light,
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

            if (recipeDetails.urlToRecipe.isNotEmpty()) {
                val context = LocalContext.current

                Text(
                    modifier = labelModifier,
                    fontWeight = FontWeight.Light,
                    text = stringResource(id = R.string.link_to_recipe),
                )
                Text(
                    modifier = valueModifier
                        .clickable { context.navigateToUrl(recipeDetails.urlToRecipe) },
                    text = recipeDetails.urlToRecipe,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            }
            if (recipeDetails.ingredients.isNotEmpty()) {
                Text(
                    modifier = labelModifier,
                    fontWeight = FontWeight.Light,
                    text = stringResource(id = R.string.ingredients),
                )
            }
        }

        items(recipeDetails.ingredients) {
            ListRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 0.dp,
                        vertical = 4.dp
                    ),
                text = it
            )
        }

        item {
            if (recipeDetails.recipe.isNotEmpty()) {
                Text(
                    modifier = labelModifier,
                    fontWeight = FontWeight.Light,
                    text = stringResource(id = R.string.recipe),
                )
                Text(
                    modifier = valueModifier,
                    text = recipeDetails.recipe,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(
                    modifier = Modifier
                        .height(80.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeDetailsScaffoldPreview() {
    MyRecipeBookTheme {
        RecipeDetailsScaffold({ }, { }, { }, { }, { }, UiState.Details(
            RecipeDetails(
                name = "Recipe",
                timeToPrepare = "5h",
                rate = "5",
                urlToRecipe = "wefwr.przepisy.pl",
                ingredients = listOf(),
                recipe = "",
                imageResultUri = ""
            ), false
        ), 1
        )
    }
}

@Preview
@Composable
fun RecipeDetailsScaffoldDialogPreview() {
    MyRecipeBookTheme {
        RecipeDetailsScaffold({ }, { }, { }, { }, { }, UiState.Details(
            RecipeDetails(
                name = "Recipe",
                timeToPrepare = "5h",
                rate = "5",
                urlToRecipe = "www.przepisy.pl",
                ingredients = listOf(),
                recipe = "",
                imageResultUri = "",
            ), true
        ), 1
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
            Modifier, RecipeDetails(
                name = "Spaghetti Bolognese",
                timeToPrepare = "4h",
                rate = "9",
                urlToRecipe = "www.przepisy.pl",
                ingredients = listOf(
                    "Onions", "Potatoes", "Cucumber"
                ),
                recipe = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                imageResultUri = ""
            )
        )
    }
}
