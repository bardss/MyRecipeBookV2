package com.jakubaniola.designsystem.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CircularFloatingActionButton(
    icon: ImageVector,
    contentDescription: String
) {
    FloatingActionButton(
        shape = RoundedCornerShape(70.dp),
        onClick = {},
        modifier = Modifier
            .padding(16.dp)
            .height(70.dp)
            .width(70.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
        )
    }
}
