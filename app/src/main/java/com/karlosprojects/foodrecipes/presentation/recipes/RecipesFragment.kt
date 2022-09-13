package com.karlosprojects.foodrecipes.presentation.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.karlosprojects.foodrecipes.R
import com.karlosprojects.foodrecipes.core.utils.UiEvent
import com.karlosprojects.foodrecipes.databinding.FragmentRecipesBinding
import com.karlosprojects.foodrecipes.presentation.extensions.showStringSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel by viewModels()
    private lateinit var binding: FragmentRecipesBinding

    private val recipesAdapter by lazy { RecipesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recipes,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        recipesViewModel.getRecipes()
    }

    private fun initObservers() {
        with(binding) {
            recipesViewModel.uiEvent.onEach { event ->
                when (event) {
                    UiEvent.ShowEmptyState -> {
                        errorNetworkImg.visibility = View.VISIBLE
                        errorNetworkMessage.visibility = View.VISIBLE
                    }
                    is UiEvent.ShowSnackBar -> {
                        root.showStringSnackBar(event.message.asString(requireContext()))
                    }
                    else -> Unit
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            recipesViewModel.recipesState.onEach { state ->
                if (state.isLoading) {
                    recipesRecycler.visibility = View.GONE
                    recipesLoading.visibility = View.VISIBLE
                } else {
                    recipesLoading.visibility = View.GONE
                }
                if (state.recipes.isNotEmpty()) {
                    recipesRecycler.visibility = View.VISIBLE
                    recipesAdapter.submitList(state.recipes)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun initRecyclerView() {
        binding.recipesRecycler.adapter = recipesAdapter
        binding.recipesRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

}