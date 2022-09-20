package com.karlosprojects.foodrecipes.data.local.datastore

data class DataStoreParameters(
    val name: String,
    val mealType: String,
    val mealTypeId: Int,
    val dietType: String,
    val dietTypeId: Int,
    val backOnline: Boolean
) {
}