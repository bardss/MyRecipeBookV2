package com.jakubaniola.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ListEmptyState(
    modifier: Modifier,
    resourceText: Int
) {
    Text(
        text = stringResource(resourceText),
        modifier = modifier
            .padding(80.dp),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center
    )
}
