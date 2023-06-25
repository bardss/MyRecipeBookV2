package com.jakubaniola.designsystem.components.fab

import androidx.compose.ui.graphics.vector.ImageVector

data class FabState(
    val icon: ImageVector,
    val contentDescription: String,
    val isEnabled: Boolean = true,
    val onClick: () -> Unit,
)
