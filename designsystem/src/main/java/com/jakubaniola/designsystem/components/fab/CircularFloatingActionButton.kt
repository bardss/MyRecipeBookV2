package com.jakubaniola.designsystem.components.fab

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularFloatingActionButton(
    state: FabState
) {
    val modifier = Modifier
        .padding(16.dp)
        .height(70.dp)
        .width(70.dp)
    val shape = RoundedCornerShape(70.dp)
    if (state.isEnabled) {
        FloatingActionButton(
            shape = shape,
            onClick = state.onClick,
            modifier = modifier
        ) {
            Icon(
                imageVector = state.icon,
                contentDescription = state.contentDescription,
            )
        }
    } else {
        FloatingActionButton(
            shape = shape,
            onClick = { },
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.outlineVariant,
            contentColor = MaterialTheme.colorScheme.outline,
        ) {
            Icon(
                imageVector = state.icon,
                contentDescription = state.contentDescription,
            )
        }
    }
}
