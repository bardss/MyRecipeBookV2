package com.jakubaniola.addrecipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.common.R
import com.jakubaniola.designsystem.components.FormField
import com.jakubaniola.designsystem.components.MrbScaffold

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
                it,
                viewModel,
                onAddSuccess
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

        FormField(
            fieldValue = uiState.name,
            labelStringId = R.string.name,
            modifier = rowModifier,
            onValueChange = onNameChange
        )

        Row(
            modifier = rowModifier
        ) {
            val spacing = 8.dp
            FormField(
                fieldValue = uiState.prepTime,
                labelStringId = R.string.prep_time,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = spacing
                    ),
                onValueChange = onPrepTimeChange
            )
            FormField(
                fieldValue = uiState.rate,
                labelStringId = R.string.rate_out_of_10,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = spacing
                    ),
                onValueChange = onRateChange
            )
        }
        FormField(
            fieldValue = uiState.recipe,
            labelStringId = R.string.recipe,
            modifier = rowModifier,
            onValueChange = onRecipeChange
        )
        FormField(
            fieldValue = uiState.linkToRecipe,
            labelStringId = R.string.link_to_recipe,
            modifier = rowModifier,
            onValueChange = onLinkToRecipeChange
        )
    }
}
