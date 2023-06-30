package com.jakubaniola.recipeslist.recipeslist

fun List<RecipeItem>.filter(query: String) =
    filter { it.name.contains(query) }