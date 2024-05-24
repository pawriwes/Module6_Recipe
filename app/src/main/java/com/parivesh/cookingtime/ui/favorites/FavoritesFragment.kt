package com.parivesh.cookingtime.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parivesh.cookingtime.Model.Recipe
import com.parivesh.cookingtime.R

class FavoritesFragment : Fragment() {

    private lateinit var adapter: FavoritesAdapter
    private val favoriteRecipes = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add 10 items to the list for demonstration purposes
        repeat(10) {
            favoriteRecipes.add(
                Recipe(
                    idMeal = "52767",
                    strMeal = "Teriyaki Chicken Casserole",
                    strMealThumb = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
                    isFavorite = true
                )
            )
        }

        adapter = FavoritesAdapter(favoriteRecipes)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewFavorites)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}

