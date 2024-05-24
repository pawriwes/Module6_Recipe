package com.parivesh.cookingtime.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parivesh.cookingtime.R
import com.parivesh.cookingtime.Model.Recipe

class FavoritesAdapter(
    private val recipes: MutableList<Recipe>
) : RecyclerView.Adapter<FavoritesAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgThumbnail: ImageView = itemView.findViewById(R.id.imgThumbnail)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)

        fun bind(recipe: Recipe) {
            // Bind data to views programmatically
            Glide.with(imgThumbnail.context)
                .load("https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg")
                .placeholder(R.drawable.baseline_3p_24)
                .into(imgThumbnail)


            tvTitle.text = recipe.strMeal

            btnFavorite.setOnClickListener {
                recipe.isFavorite = !recipe.isFavorite
                if (!recipe.isFavorite) {
                    removeRecipe(recipe)
                } else {
                    notifyItemChanged(adapterPosition)
                }
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

    private fun removeRecipe(recipe: Recipe) {
        val position = recipes.indexOf(recipe)
        if (position != -1) {
            recipes.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
