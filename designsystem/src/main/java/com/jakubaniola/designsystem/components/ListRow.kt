package com.jakubaniola.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun ListRow(
    modifier: Modifier = Modifier,
    text: String,
    isRemovable: Boolean = false,
    onRemoveClick: () -> Unit = { },
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
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
            if (isRemovable) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Remove icon",
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListRowPreview() {
    MyRecipeBookTheme {
        ListRow(
            text = "Onion"
        ) {
        }
    }
}
