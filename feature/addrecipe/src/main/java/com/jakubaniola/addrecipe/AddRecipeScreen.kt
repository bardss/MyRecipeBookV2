package com.jakubaniola.addrecipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.designsystem.components.MrbScaffold
import com.jakubaniola.recipeslist.R

@Composable
fun AddRecipeScreen(
    onAddSuccess: () -> Unit,
    viewModel: AddRecipeViewModel = hiltViewModel(),
) {
    MrbScaffold(
        topBarTitle = R.string.add_recipe,
        fabIcon = Icons.Default.Check,
        fabContentDescription = "Save recipe button",
        onFabClick = viewModel::onSaveClick,
        content = {
            AddRecipeContent(
                it, viewModel, onAddSuccess
            )
        }
    )
}

@Composable
fun AddRecipeContent(
    paddingValues: PaddingValues,
    viewModel: AddRecipeViewModel,
    onAddSuccess: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    when (uiState) {
        is UiState.Adding -> {
            AddRecipeForm(
                paddingValues = paddingValues,
                onNameChange = viewModel::onNameChange,
                onPrepTimeChange = viewModel::onPrepTimeChange,
                onRateChange = viewModel::onRateChange,
                onRecipeChange = viewModel::onRecipeChange,
                onLinkToRecipeChange = viewModel::onLinkToRecipeChange,
                uiState = uiState.state
            )
        }
        is UiState.OnAddSuccess -> onAddSuccess()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddRecipeForm(
    paddingValues: PaddingValues,
    onNameChange: (String) -> Unit,
    onPrepTimeChange: (String) -> Unit,
    onRateChange: (String) -> Unit,
    onRecipeChange: (String) -> Unit,
    onLinkToRecipeChange: (String) -> Unit,
    uiState: AddRecipeState
) {
    val horizontalPadding = 16.dp
    val verticalPadding = 4.dp

    Column(
        modifier = Modifier
            .padding(
                paddingValues
            )
    ) {
        val rowModifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )

        OutlinedTextField(
            modifier = rowModifier,
            value = uiState.name,
            onValueChange = onNameChange,
            label = { Text(text = stringResource(id = R.string.name)) },
            maxLines = 1
        )

        Row(
            modifier = rowModifier
        ) {
            val spacing = 8.dp
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = spacing
                    ),
                value = uiState.prepTime,
                onValueChange = onPrepTimeChange,
                label = { Text(text = stringResource(id = R.string.prep_time)) },
                maxLines = 1
            )
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = spacing
                    ),
                value = uiState.rate,
                onValueChange = onRateChange,
                label = { Text(text = stringResource(id = R.string.rate)) },
                maxLines = 1
            )
        }

        OutlinedTextField(
            modifier = rowModifier,
            value = uiState.recipe,
            onValueChange = onRecipeChange,
            label = { Text(text = stringResource(id = R.string.recipe)) },
        )

        OutlinedTextField(
            modifier = rowModifier,
            value = uiState.linkToRecipe,
            onValueChange = onLinkToRecipeChange,
            label = { Text(text = stringResource(id = R.string.link_to_recipe)) },
        )
    }
}
