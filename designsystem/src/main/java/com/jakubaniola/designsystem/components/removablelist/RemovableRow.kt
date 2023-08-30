package com.jakubaniola.designsystem.components.removablelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme

@Composable
fun RemovableRow(
    modifier: Modifier = Modifier,
    text: String,
    onRemoveClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onRemoveClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "Remove icon",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RemovableRowPreview() {
    MyRecipeBookTheme {
        RemovableRow(
            text = "Onion"
        ) {
        }
    }
}
