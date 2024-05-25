package com.parivesh.cookingtime.repository

import androidx.lifecycle.LiveData
import com.parivesh.cookingtime.model.Recipe
import com.parivesh.cookingtime.model.RecipeDao

class FavoritesRepository(private val recipeDao: RecipeDao) {
    val favoriteRecipes: LiveData<List<Recipe>> = recipeDao.getFavoriteRecipes()

    suspend fun insert(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun delete(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }
}
