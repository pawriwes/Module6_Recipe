package com.parivesh.cookingtime.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parivesh.cookingtime.Model.Recipe
import com.parivesh.cookingtime.R

class HomeAdapter(
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<HomeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgThumbnail: ImageView = itemView.findViewById(R.id.imgThumbnail)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)

        fun bind(recipe: Recipe) {
            // Bind data to views programmatically
            Glide.with(imgThumbnail.context)
                .load(recipe.strMealThumb)
                .placeholder(R.drawable.baseline_3p_24)
                .into(imgThumbnail)

            tvTitle.text = recipe.strMeal

            // Set favorite button state
            btnFavorite.setImageResource(
                if (recipe.isFavorite) R.drawable.baseline_favorite_24
                else R.drawable.baseline_favorite_border_24
            )

            btnFavorite.setOnClickListener {
                recipe.isFavorite = !recipe.isFavorite
                changeFavorite(recipe)
            }

            itemView.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favorite_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.size

    private fun changeFavorite(recipe: Recipe) {
        val position = recipes.indexOf(recipe)
        if (position != -1) {
            notifyItemChanged(position)
        }
    }
}