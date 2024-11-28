package com.karlosprojects.foodrecipes.presentation.recipes.recipeingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.core.utils.Constants.BASE_IMAGE_URL
import com.karlosprojects.foodrecipes.databinding.ItemIngredientBinding
import com.karlosprojects.foodrecipes.domain.model.Recipes

class IngredientsAdapter :
    ListAdapter<Recipes.Ingredients, IngredientsAdapter.IngredientsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientBinding.inflate(layoutInflater, parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.bind(currentItem)
    }

    class IngredientsViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Recipes.Ingredients) {
            with(binding) {
                loadIngredient(ingredientImageView, ingredient.image)
                ingredientName.text = ingredient.name
                ingredientAmount.text = ingredient.amount.toString()
                ingredientUnit.text = ingredient.unit
                ingredientConsistency.text = ingredient.consistency
                ingredientOriginal.text = ingredient.original
            }
        }

        private fun loadIngredient(imageView: AppCompatImageView, imageUrl: String) {
            imageView.load(BASE_IMAGE_URL + imageUrl) {
                crossfade(600)
                error(R.drawable.error_img_placeholder)
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