package com.karlosprojects.foodrecipes.presentation.recipes.recipeinstructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.karlosprojects.foodrecipes.core.utils.Constants
import com.karlosprojects.foodrecipes.databinding.FragmentInstructionsBinding
import com.karlosprojects.foodrecipes.domain.model.Recipes

class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    private val recipe: Recipes?
        get() = arguments?.getParcelable(Constants.RECIPE_ARGUMENT)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipe?.let {
            binding.instructionsWebView.apply {
                webViewClient = object : WebViewClient() {}
                loadUrl(it.sourceUrl)

            }
        }

    }

}