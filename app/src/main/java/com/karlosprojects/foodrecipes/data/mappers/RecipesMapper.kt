package com.karlosprojects.foodrecipes.data.mappers

import com.karlosprojects.foodrecipes.data.dto.ExtendedIngredient
import com.karlosprojects.foodrecipes.data.dto.RecipeDto
import com.karlosprojects.foodrecipes.domain.model.Recipes

fun RecipeDto.toDomainRecipe(): Recipes {

    return Recipes(
        aggregateLikes = aggregateLikes,
        cheap = cheap,
        dairyFree = dairyFree,
        extendedIngredients = extendedIngredients.getIngredients(),
        glutenFree = glutenFree,
        id = id,
        image = image,
        readyInMinutes = readyInMinutes,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        summary = summary,
        title = title,
        vegan = vegan,
        vegetarian = vegetarian,
        veryHealthy = veryHealthy,
    )
}

private fun List<ExtendedIngredient>.getIngredients(): List<Recipes.Ingredients> {
    val ingredients = mutableListOf<Recipes.Ingredients>()
    forEach { ingredient ->
        ingredients.add(
            Recipes.Ingredients(
                amount = ingredient.amount,
                consistency = ingredient.consistency,
                image = ingredient.image.takeUnless { it.isNullOrEmpty() } ?: "",
                name = ingredient.name,
                original = ingredient.original,
                unit = ingredient.unit
            )
        )
    }
    return ingredients
}
