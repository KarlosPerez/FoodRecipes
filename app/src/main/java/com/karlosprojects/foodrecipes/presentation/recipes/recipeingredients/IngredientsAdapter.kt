package com.karlosprojects.foodrecipes.presentation.recipes.recipeingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karlosprojects.foodrecipes.databinding.ItemIngredientBinding
import com.karlosprojects.foodrecipes.domain.model.Recipes

class IngredientsAdapter :
    ListAdapter<Recipes.Ingredients, IngredientsAdapter.IngredientsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.bind(currentItem)
    }

    class IngredientsViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Recipes.Ingredients) {
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): IngredientsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIngredientBinding.inflate(layoutInflater, parent, false)
                return IngredientsViewHolder(binding)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Recipes.Ingredients>() {

        override fun areItemsTheSame(oldItem: Recipes.Ingredients, newItem: Recipes.Ingredients) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Recipes.Ingredients,
            newItem: Recipes.Ingredients
        ) =
            oldItem == newItem

    }
}