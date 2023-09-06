package com.jakubaniola.recipedetails

import androidx.lifecycle.SavedStateHandle
import com.jakubaniola.model.Recipe
import com.jakubaniola.recipedetails.navigation.ARG_RECIPE_ID
import com.jakubaniola.repository.RecipeRepositoryImpl
import com.jakubaniola.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeDetailsViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val recipeId = 1234
    private val recipe: Recipe = Recipe(
        id = recipeId,
        name = "Recipe",
        timeToPrepare = "2h",
        rate = "4",
        resultPhotoPath = "photo.jpg",
        urlToRecipe = "url.pl",
        ingredients = "Ingredients",
        recipe = "Mix it all"
    )
    private lateinit var recipeRepository: RecipeRepositoryImpl
    private lateinit var viewModel: RecipeDetailsViewModel

    @Before
    fun init() {
        recipeRepository = mockk()
        coEvery { recipeRepository.getRecipe(recipeId) } returns flowOf(recipe)
        coEvery { recipeRepository.removeRecipe(recipeId) } returns Unit

        viewModel = RecipeDetailsViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(ARG_RECIPE_ID to recipeId)
            ),
            recipeRepository = recipeRepository
        )
    }

    @Test
    fun `ui state starts with recipe received from repository`() {
        assertEquals(
            UiState.Details(
                state = recipe.toDetails()
            ),
            viewModel.uiState.value
        )
    }

    @Test
    fun `confirm remove click removes recipe from repository`() {
        viewModel.onConfirmRemoveClick()

        coVerify(exactly = 1) {
            recipeRepository.removeRecipe(recipeId)
        }
    }

    @Test
    fun `confirm remove click changes ui state to OnRemoveSuccess`() {
        viewModel.onConfirmRemoveClick()

        assertEquals(
            UiState.OnRemoveSuccess,
            viewModel.uiState.value
        )
    }

    @Test
    fun `on remove click state of dialog is changed to visible`() {
        viewModel.onRemoveClick()

        assertEquals(
            true,
            (viewModel.uiState.value as UiState.Details).isRemoveDialogVisible
        )
    }


    @Test
    fun `on cancel remove click state of dialog is changed to not visible`() {
        viewModel.onCancelRemoveClick()

        assertEquals(
            false,
            (viewModel.uiState.value as UiState.Details).isRemoveDialogVisible
        )
    }
}