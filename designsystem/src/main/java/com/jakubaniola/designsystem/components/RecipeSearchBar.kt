package com.jakubaniola.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeSearchBar(
    searchQuery: String,
    isOrderByRateOn: Boolean,
    onValueChange: (String) -> Unit,
    onOrderByRateClick: () -> Unit,
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
                    contentDescription = "Search icon",
                    modifier = Modifier
                        .padding(start = 6.dp)
                )
            },
            trailingIcon = {
                val icon = if (isOrderByRateOn) {
                    Icons.Filled.Favorite
                } else {
                    Icons.TwoTone.Favorite
                }
                Icon(
                    imageVector = icon,
                    contentDescription = "Toggle search by starred recipes",
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .clickable { onOrderByRateClick() }
                )
            }
        )
    }
}

@Preview
@Composable
fun RecipeSearchBarPreviewOrderOff() {
    RecipeSearchBar(
        searchQuery = "Search query",
        isOrderByRateOn = false,
        onValueChange = { },
        onOrderByRateClick = { },
    )
}

@Preview
@Composable
fun RecipeSearchBarPreviewOrderOn() {
    RecipeSearchBar(
        searchQuery = "Search query",
        isOrderByRateOn = true,
        onValueChange = { },
        onOrderByRateClick = { },
    )
}
