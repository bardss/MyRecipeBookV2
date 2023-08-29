@file:OptIn(ExperimentalMaterial3Api::class)

package com.jakubaniola.designsystem.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
            AsyncImage(
                model = Uri.parse(imageUri),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
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
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = rateLabel,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Text(
                text = rate,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
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
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Text(
                text = prepTime,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    ),
            )
        }
    }
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
