package com.jakubaniola.addrecipe

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddRecipeScreen(
    paddingValues: PaddingValues
) {
    Text(
        text = "Hello - Add recipe  screen",
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .fillMaxWidth()
    )
}
