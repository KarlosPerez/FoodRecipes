package com.karlosprojects.foodrecipes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Recipes(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients: @RawValue List<Ingredients>,
    val glutenFree: Boolean,
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
) : Parcelable {

    @Parcelize
    data class Ingredients(
        val amount: Double,
        val consistency: String,
        val image: String,
        val name: String,
        val original: String,
        val unit: String
    ): Parcelable
}
