package com.jakubaniola.myrecipebook.ui.mainlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakubaniola.components.CircularFloatingActionButton
import com.jakubaniola.components.RecipeGridListItem
import com.jakubaniola.components.RecipeSearchBar
import com.jakubaniola.components.TopBar
import com.jakubaniola.myrecipebook.R
import com.jakubaniola.myrecipebook.ui.theme.MyRecipeBookTheme

@ExperimentalMaterial3Api
@Composable
fun RecipesListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(stringResource(id = R.string.app_name))
        },
        floatingActionButton = {
            CircularFloatingActionButton(
                icon = Icons.Default.Add,
                contentDescription = "Add new recipe button"
            )
        },
        content = { paddingValues ->
            RecipesListContent(modifier, paddingValues)
        },
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun RecipesListContent(
    modifier: Modifier,
    paddingValues: PaddingValues
) {
    val recipes = listOf(
        RecipeListItem("Curry", "Rate: 3/4", "Prep: 2h", "https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-vegetables.jpg"),
        RecipeListItem("Taco", "Rate: 3/10", "Prep: Some time", "https://cdn.loveandlemons.com/wp-content/uploads/2020/06/picnic-food.jpg"),
        RecipeListItem("Schabowe", "Rate: 3/4", "Prep: 2h", "https://media.cnn.com/api/v1/images/stellar/prod/140430115517-06-comfort-foods.jpg"),
        RecipeListItem("Lovely lovik", "Rate: 3/4", "Prep: 2h", "https://www.teenaagnel.com/wp-content/uploads/2019/12/food-photography-in-dubai.jpg"),
        RecipeListItem("Curry", "Rate: 3/4", "Prep: 2h", "https://hips.hearstapps.com/hmg-prod/images/delish-210419-carne-asada-torta-01-portrait-jg-1620136948.jpg"),
        RecipeListItem("Curry", "Rate: 3/4", "Prep: 2h", "https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-vegetables.jpg"),
        RecipeListItem("Taco", "Rate: 3/10", "Prep: Some time", "https://cdn.loveandlemons.com/wp-content/uploads/2020/06/picnic-food.jpg"),
        RecipeListItem("Schabowe", "Rate: 3/4", "Prep: 2h", "https://media.cnn.com/api/v1/images/stellar/prod/140430115517-06-comfort-foods.jpg"),
        RecipeListItem("Lovely lovik", "Rate: 3/4", "Prep: 2h", "https://www.teenaagnel.com/wp-content/uploads/2019/12/food-photography-in-dubai.jpg"),
    )
    Column(
        modifier = modifier
            .padding(
                top = paddingValues.calculateTopPadding()
            )
    ) {
        RecipeSearchBar {
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 120.dp
            ),
            content = {
                items(recipes) { recipe ->
                    RecipeGridListItem(
                        name = recipe.name,
                        rate = recipe.rate,
                        prepTime = recipe.prepTime,
                        image = recipe.image,
                        onGridListItemClick = {}
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RecipesListScreenPreview() {
    MyRecipeBookTheme {
        RecipesListScreen()
    }
}

@Preview
@Composable
fun RecipesListContentPreview() {
    MyRecipeBookTheme {
        RecipesListContent(
            Modifier,
            PaddingValues(0.dp)
        )
    }
}

data class RecipeListItem(
    val name: String,
    val rate: String,
    val prepTime: String,
    val image: String
)