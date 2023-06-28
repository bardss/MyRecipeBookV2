package com.jakubaniola.recipedetails

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.jakubaniola.model.Recipe
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val recipeId = 1234
    private val recipe: Recipe = Recipe(
        id = recipeId,
        name = "Recipe name",
        timeToPrepare = "2h",
        rate = "4",
        resultPhotoPath = "photo.jpg",
        urlToRecipe = "url.pl",
        ingredients = "Ingredients",
        recipe = "Mix it all"
    )

    @Test
    fun screenShowsRecipeDetails() {
        val uiState = UiState.Details(
            state = recipe.toDetails(),
            isRemoveDialogVisible = false
        )

        composeTestRule.setContent {
            RecipeDetailsScreen(
                navigateToEditRecipe = { },
                navigateBack = { },
                onRemoveClick = { },
                onConfirmRemoveClick = { },
                onCancelRemoveClick = { },
                uiState = uiState,
                recipeId = recipeId,
            )
        }

        composeTestRule
            .onNodeWithText(recipe.name)
            .assertExists()

        composeTestRule
            .onNodeWithText(recipe.rate)
            .assertExists()

        composeTestRule
            .onNodeWithText(recipe.timeToPrepare)
            .assertExists()
    }
}