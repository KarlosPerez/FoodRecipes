package com.karlosprojects.foodrecipes.presentation.recipes.recipelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.textview.MaterialTextView
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.databinding.ItemRecipeBinding
import com.karlosprojects.foodrecipes.domain.model.Recipes
import com.karlosprojects.foodrecipes.presentation.extensions.getHtmlString

class RecipesAdapter : ListAdapter<Recipes, RecipesAdapter.RecipesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(layoutInflater, parent, false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.bind(currentItem)
    }

    inner class RecipesViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemRecipeImgDetails.setOnClickListener {
                val action = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetail(
                    currentList[adapterPosition]
                )
                binding.itemRecipeImgDetails.findNavController().navigate(action)
            }
        }

        fun bind(recipe: Recipes) {
            with(binding) {
                loadImageFromUrl(itemRecipeImg, recipe.image)
                itemRecipeName.text = recipe.title
                parseHtml(itemRecipeDescription, recipe.summary, binding.root.context)
                setNumbersOfLikes(itemRecipeLikesTitle, recipe.aggregateLikes)
                setPreparationTime(itemRecipePreparationTimeTitle, recipe.readyInMinutes)
                applyVeganColor(itemRecipeImgVegan, recipe.vegan)
                applyVeganColor(itemRecipeVeganTitle, recipe.vegan)
            }
        }

        private fun setNumbersOfLikes(textView: MaterialTextView, likes: Int) {
            textView.text = likes.toString()
        }

        private fun loadImageFromUrl(imageView: AppCompatImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.error_img_placeholder)
            }
        }

        private fun parseHtml(textView: MaterialTextView, text: String, context: Context) {
            textView.text = context.getHtmlString(text)
        }

        private fun setPreparationTime(textView: MaterialTextView, preparationTime: Int) {
            textView.text = preparationTime.toString()
        }

        private fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    is MaterialTextView -> {
                        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                    }

                    is AppCompatImageView -> {
                        view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                    }
                }
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