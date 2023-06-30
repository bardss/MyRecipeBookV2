package com.jakubaniola.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearchBar(
    searchQuery: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchQuery,
            onValueChange = {
                onValueChange(it)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            },
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Star,
//                    contentDescription = "Toggle search by starred recipes"
//                )
//            }
        )
    }
}

@Preview
@Composable
fun RecipeSearchBarPreview() {
    RecipeSearchBar(
        searchQuery = "Search query",
        onValueChange = { }
    )
}
