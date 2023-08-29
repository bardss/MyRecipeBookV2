package com.jakubaniola.addrecipe

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.common.R
import com.jakubaniola.common.grantPersistentUriPermission
import com.jakubaniola.designsystem.components.FormField
import com.jakubaniola.designsystem.components.MrbScaffold
import com.jakubaniola.designsystem.components.PickImage
import com.jakubaniola.designsystem.components.fab.FabState

@Composable
fun AddEditRecipeScreen(
    onAddSuccess: () -> Unit,
    viewModel: AddEditRecipeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Log.e("Jakub123", "SCREEN uiState1: ${uiState}")
    val isFabEnabled = if (uiState is UiState.AddEdit) uiState.state.isSaveEnabled else false

    MrbScaffold(
        topBarTitle = R.string.add_recipe,
        fabStates = listOf(
            FabState(
                icon = Icons.Default.Check,
                contentDescription = "Save recipe button",
                isEnabled = isFabEnabled,
                onClick = viewModel::onSaveClick,
            )
        ),
        content = {
            AddEditRecipeContent(
                it,
                viewModel,
                onAddSuccess
            )
        }
    )
}

@Composable
fun AddEditRecipeContent(
    paddingValues: PaddingValues,
    viewModel: AddEditRecipeViewModel,
    onAddSuccess: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Log.e("Jakub123", "SCREEN uiState2: ${uiState}")

    when (uiState) {
        is UiState.AddEdit -> {
            AddEditRecipeForm(
                paddingValues = paddingValues,
                onNameChange = viewModel::onNameChange,
                onPrepTimeChange = viewModel::onPrepTimeChange,
                onRateChange = viewModel::onRateChange,
                onRecipeChange = viewModel::onRecipeChange,
                onLinkToRecipeChange = viewModel::onLinkToRecipeChange,
                onImageUpdate = viewModel::onImageUpdate,
                uiState = uiState.state
            )
        }

        is UiState.OnSaveSuccess -> onAddSuccess()
        is UiState.Loading -> Column {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(80.dp)
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
private fun AddEditRecipeForm(
    paddingValues: PaddingValues,
    onNameChange: (String) -> Unit,
    onPrepTimeChange: (String) -> Unit,
    onRateChange: (String) -> Unit,
    onRecipeChange: (String) -> Unit,
    onLinkToRecipeChange: (String) -> Unit,
    onImageUpdate: (String?) -> Unit,
    uiState: AddEditRecipeState
) {
    val horizontalPadding = 16.dp
    val verticalPadding = 4.dp
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.grantPersistentUriPermission(context)
            onImageUpdate(uri.toString())
        }
    )

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(paddingValues)
            .imePadding()
            .imeNestedScroll()
            .padding(0.dp, 0.dp, 0.dp, 20.dp)
    ) {
        val rowModifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )

        PickImage(
            imageUri = uiState.imageResultUri,
            onClick = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )

        FormField(
            fieldValue = uiState.name,
            labelStringId = R.string.name,
            modifier = rowModifier,
            onValueChange = onNameChange,
            isThereNextField = true,
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
                onValueChange = onPrepTimeChange,
                isThereNextField = true,
            )
            FormField(
                fieldValue = uiState.rate,
                labelStringId = R.string.rate_out_of_10,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = spacing
                    ),
                onValueChange = onRateChange,
                keyboardType = KeyboardType.Number,
                isThereNextField = true,
            )
        }
        FormField(
            fieldValue = uiState.linkToRecipe,
            labelStringId = R.string.link_to_recipe,
            modifier = rowModifier,
            onValueChange = onLinkToRecipeChange,
            isThereNextField = true,
        )
        FormField(
            fieldValue = uiState.recipe,
            labelStringId = R.string.recipe,
            modifier = rowModifier,
            onValueChange = onRecipeChange,
            maxLines = 50
        )
    }
}
