package com.karlosprojects.foodrecipes.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karlosprojects.foodrecipes.core.utils.Constants.FAVORITES_TABLE
import com.karlosprojects.foodrecipes.domain.model.Recipes

@Entity(tableName = FAVORITES_TABLE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var recipe: Recipes
)
