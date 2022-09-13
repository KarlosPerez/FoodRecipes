package com.karlosprojects.foodrecipes.presentation.bindingadapters

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.textview.MaterialTextView
import com.karlosprojects.foodrecipes.R

class ItemRecipeBinding {

    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: AppCompatImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)

            }
        }

        @BindingAdapter("setNumbersOfLikes")
        @JvmStatic
        fun setNumbersOfLikes(textView: MaterialTextView, likes: Int) {
            textView.text = likes.toString()
        }

        @BindingAdapter("setPreparationTime")
        @JvmStatic
        fun setPreparationTime(textView: MaterialTextView, preparationTime: Int) {
            textView.text = preparationTime.toString()
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
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
}