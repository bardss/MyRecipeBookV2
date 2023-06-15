package com.jakubaniola.recipeslist.recipeslist

import androidx.lifecycle.ViewModel
import com.jakubaniola.common.UiState
import com.jakubaniola.recipeslist.R
import com.jakubaniola.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipes: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading)
    val recipes: StateFlow<UiState> = _recipes

}

val placeholderRecipes = RecipesListState(
    recipes = listOf(
        RecipeItem(
            name = "Fruits",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-" +
                    "vegetables.jpg"
        ),
        RecipeItem(
            name = "Picnic food",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://cdn.loveandlemons.com/wp-content/uploads/2020/06/picnic-food.jpg"
        ),
        RecipeItem(
            name = "Comfort food",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://media.cnn.com/api/v1/images/stellar/prod/140430115517-06-" +
                    "comfort-foods.jpg"
        ),
        RecipeItem(
            name = "Dubai food",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://www.teenaagnel.com/wp-content/uploads/2019/12/food-photography-" +
                    "in-dubai.jpg"
        ),
        RecipeItem(
            name = "Carne asada",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://hips.hearstapps.com/hmg-prod/images/delish-210419-carne-asada-" +
                    "torta-01-portrait-jg-1620136948.jpg"
        ),
        RecipeItem(
            name = "Curry",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-" +
                    "vegetables.jpg"
        ),
        RecipeItem(
            name = "Fruits",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-" +
                    "vegetables.jpg"
        ),
        RecipeItem(
            name = "Picnic food",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://cdn.loveandlemons.com/wp-content/uploads/2020/06/picnic-food.jpg"
        ),
        RecipeItem(
            name = "Comfort food",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://media.cnn.com/api/v1/images/stellar/prod/140430115517-06-" +
                    "comfort-foods.jpg"
        ),
        RecipeItem(
            name = "Dubai food",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://www.teenaagnel.com/wp-content/uploads/2019/12/food-photography-" +
                    "in-dubai.jpg"
        ),
        RecipeItem(
            name = "Carne asada",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://hips.hearstapps.com/hmg-prod/images/delish-210419-carne-asada-" +
                    "torta-01-portrait-jg-1620136948.jpg"
        ),
        RecipeItem(
            name = "Curry",
            rateResource = R.string.rate,
            rateValue = "3/4",
            prepTimeResource = R.string.prep_time,
            prepTimeValue = "2h",
            image = "https://cdn.britannica.com/36/123536-050-95CB0C6E/Variety-fruits-" +
                    "vegetables.jpg"
        ),
    )
)
