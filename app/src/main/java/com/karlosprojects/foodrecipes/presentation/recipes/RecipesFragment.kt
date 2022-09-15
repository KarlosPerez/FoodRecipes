package com.karlosprojects.foodrecipes.presentation.recipes

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesAdapter by lazy { RecipesAdapter() }

    private val listener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(query: String?): Boolean {
            return true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMenu()
        initRecyclerView()
        initObservers()
        initListeners()
        recipesViewModel.getRecipes()
    }

    private fun setMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.recipes_menu, menu)
                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as? SearchView
                searchView?.let {
                    it.isSubmitButtonEnabled = true
                    it.setOnQueryTextListener(listener)
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_search -> {
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initListeners() {
        binding.recipesFab.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}