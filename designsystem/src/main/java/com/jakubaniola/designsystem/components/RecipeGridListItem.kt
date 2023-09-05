@file:OptIn(ExperimentalMaterial3Api::class)

package com.jakubaniola.designsystem.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeGridListItem(
    name: String,
    rateLabel: String,
    rate: String,
    prepTimeLabel: String,
    prepTime: String,
    imageUri: String,
    onGridListItemClick: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = MaterialTheme.shapes.small
            .copy(
                bottomStart = ShapeDefaults.ExtraLarge.bottomStart
            ),
        onClick = {
            onGridListItemClick("")
        }
    ) {
        OverlappingColumn(
            modifier = Modifier
                .fillMaxWidth(),
            overlapFactor = 0.8f
        ) {
            if (imageUri.isNotEmpty()) {
                AsyncImage(
                    model = Uri.parse(imageUri),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(50.dp, 200.dp),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
            Surface(
                shape = MaterialTheme.shapes.extraSmall
                    .copy(topEnd = ShapeDefaults.ExtraLarge.topEnd),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                RecipeGridListItemDescription(name, rateLabel, rate, prepTimeLabel, prepTime)
            }
        }
    }
}

@Composable
fun RecipeGridListItemDescription(
    name: String,
    rateLabel: String,
    rate: String,
    prepTimeLabel: String,
    prepTime: String,
) {
    Column(
        modifier = Modifier
            .background(
//                MaterialTheme.colorScheme.outlineVariant
                generateBackgroundColorFromTitle(name)
            )
            .padding(16.dp),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = rateLabel,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
            )
            Text(
                text = rate,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    )
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = prepTimeLabel,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
            )
            Text(
                text = prepTime,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    ),
            )
        }
    }
}

@Composable
private fun generateBackgroundColorFromTitle(title: String): Color {
    val colors = listOf(
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.inversePrimary,
        MaterialTheme.colorScheme.surfaceVariant,
        MaterialTheme.colorScheme.outlineVariant
    )
    return colors[abs(title.hashCode()) % colors.size]
}

@Preview(widthDp = 400, heightDp = 600)
@Composable
fun RecipeGridListItemPreview() {
    RecipeGridListItem(
        name = "Tasty panko chicken",
        rateLabel = "Rate:",
        rate = "9/10",
        prepTimeLabel = "Prep. time:",
        prepTime = "2h",
        imageUri = ""
    ) {
    }
}
