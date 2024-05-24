package com.parivesh.cookingtime.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parivesh.cookingtime.Model.Recipe
import com.parivesh.cookingtime.R
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter
    private val homeRecipes = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = HomeViewModelFactory(requireActivity().application)
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHome)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = HomeAdapter(homeRecipes) { recipe ->
            val bundle = Bundle().apply {
                putString("recipe_id", recipe.idMeal)
            }
            findNavController().navigate(R.id.action_homeFragment_to_recipeDetailFragment, bundle)
        }
        recyclerView.adapter = adapter

        // Observe the recipes LiveData
        homeViewModel.getRecipes().observe(viewLifecycleOwner, { recipes ->
            homeRecipes.clear()
            homeRecipes.addAll(recipes)
            adapter.notifyDataSetChanged()
        })

        return view
    }
}