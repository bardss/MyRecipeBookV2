package com.jakubaniola.recipeslist.recipeslist

fun List<RecipeItem>.filter(query: String, orderByRate: Boolean): List<RecipeItem> {
    var result = this.filter { it.name.contains(query) }
    if (orderByRate) {
        result = result.sortedByDescending { it.rateValue }
    }
    return result
}
