package com.parivesh.cookingtime.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes WHERE isFavorite = 1")
    fun getFavoriteRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}
