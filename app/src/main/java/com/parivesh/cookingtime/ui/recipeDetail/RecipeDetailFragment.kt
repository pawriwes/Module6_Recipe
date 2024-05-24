package com.parivesh.cookingtime.ui.recipeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.parivesh.cookingtime.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RecipeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeId = arguments?.getString("recipe_id") ?: return

        val factory = RecipeDetailViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(RecipeDetailViewModel::class.java)
        viewModel.fetchRecipeDetails(recipeId)

        viewModel.recipe.observe(viewLifecycleOwner, { recipe ->
            binding.tvRecipeTitle.text = recipe.strMeal
            binding.tvRecipeCategory.text = recipe.strCategory
            binding.tvRecipeInstructions.text = recipe.strInstructions

            Glide.with(requireContext())
                .load(recipe.strMealThumb)
                .into(binding.imgRecipeThumb)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
