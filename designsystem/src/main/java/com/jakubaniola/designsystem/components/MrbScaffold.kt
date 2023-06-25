package com.jakubaniola.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jakubaniola.common.R
import com.jakubaniola.designsystem.components.fab.CircularFloatingActionButton
import com.jakubaniola.designsystem.components.fab.FabState
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MrbScaffold(
    topBarTitle: Int,
    fabStates: List<FabState>,
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(stringResource(id = topBarTitle))
        },
        floatingActionButton = {
            Column {
                fabStates.forEach {
                    CircularFloatingActionButton(it)
                }
            }
        },
        content = { paddingValues ->
            content(paddingValues)
        },
    )
}

@Preview
@Composable
fun MrbScaffold1FabPreview() {
    MyRecipeBookTheme {
        MrbScaffold(
            topBarTitle = R.string.recipe,
            fabStates = listOf(
                FabState(
                    icon = Icons.Default.Delete,
                    contentDescription = "Edit recipe button",
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    onClick = { },
                ),
                FabState(
                    icon = Icons.Default.Edit,
                    contentDescription = "Thumbs up button",
                    onClick = { },
                ),
            )
        )
    }
}

@Preview
@Composable
fun MrbScaffold2FabPreview() {
    MyRecipeBookTheme {
        MrbScaffold(
            topBarTitle = R.string.recipe,
            fabStates = listOf(
                FabState(
                    icon = Icons.Default.Edit,
                    contentDescription = "Thumbs up button",
                    onClick = { },
                ),
            )
        )
    }
}
