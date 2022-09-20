package com.karlosprojects.foodrecipes.presentation.recipes.recipelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karlosprojects.foodrecipes.databinding.ItemRecipeBinding
import com.karlosprojects.foodrecipes.domain.model.Recipes

class RecipesAdapter : ListAdapter<Recipes, RecipesAdapter.RecipesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.bind(currentItem)
    }

    class RecipesViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipes) {
            binding.recipe = recipe
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecipeBinding.inflate(layoutInflater, parent, false)
                return RecipesViewHolder(binding)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Recipes>() {

        override fun areItemsTheSame(oldItem: Recipes, newItem: Recipes) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipes, newItem: Recipes) =
            oldItem == newItem

    }
}