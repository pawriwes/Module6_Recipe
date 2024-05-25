package com.parivesh.cookingtime.ui.recipedetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.parivesh.cookingtime.model.Recipe
import org.json.JSONObject

class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> = _recipe

    private val requestQueue: RequestQueue = Volley.newRequestQueue(application)

    fun fetchRecipeDetail(recipeId: String) {
        val url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=$recipeId"
        Log.d("RecipeDetailViewModel", "Fetching recipe details from URL: $url")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val recipe = parseRecipe(response)
                _recipe.value = recipe
                Log.d("RecipeDetailViewModel", "Recipe details fetched and parsed: $recipe")
            },
            { error ->
                Log.e("RecipeDetailViewModel", "Error fetching recipe details", error)
            }
        )
        requestQueue.add(jsonObjectRequest)
    }

    private fun parseRecipe(response: JSONObject): Recipe {
        val mealsArray = response.getJSONArray("meals")
        val mealObject = mealsArray.getJSONObject(0)
        val gson = Gson()
        return gson.fromJson(mealObject.toString(), Recipe::class.java)
    }
}
