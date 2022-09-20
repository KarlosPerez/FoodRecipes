package com.karlosprojects.foodrecipes.presentation.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.textview.MaterialTextView
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.core.utils.Constants.BASE_IMAGE_URL

class IngredientsBinding {

    companion object {

        @BindingAdapter("loadIngredient")
        @JvmStatic
        fun loadIngredient(imageView: AppCompatImageView, imageUrl: String) {
            imageView.load(BASE_IMAGE_URL + imageUrl) {
                crossfade(600)
                error(R.drawable.error_img_placeholder)
            }
        }

        @BindingAdapter("setIngredientAmount")
        @JvmStatic
        fun setIngredientAmount(textView: MaterialTextView, amount: Double) {
            textView.text = amount.toString()
        }
    }
}