package com.jakubaniola.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MrbScaffold(
    topBarTitle: Int,
    fabState: FabState,
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(stringResource(id = topBarTitle))
        },
        floatingActionButton = {
            CircularFloatingActionButton(fabState)
        },
        content = { paddingValues -> content(paddingValues) },
    )
}
