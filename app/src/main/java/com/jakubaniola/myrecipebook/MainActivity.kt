package com.jakubaniola.myrecipebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.jakubaniola.designsystem.theme.theme.MyRecipeBookTheme
import com.jakubaniola.recipeslist.recipeslist.RecipesListScreen

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRecipeBookTheme {
                RecipesListScreen()
            }
        }
    }
}
