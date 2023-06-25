package com.jakubaniola.designsystem.components.fab

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class FabState(
    val icon: ImageVector,
    val contentDescription: String,
    val isEnabled: Boolean = true,
    val containerColor: Color? = null,
    val contentColor: Color? = null,
    val onClick: () -> Unit,
)
