package com.parivesh.cookingtime.ui.recipeDetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.parivesh.cookingtime.Model.Recipe
import org.json.JSONObject
import androidx.lifecycle.AndroidViewModel


class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> get() = _recipe

    fun fetchRecipeDetails(recipeId: String) {
        val url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=$recipeId"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val mealsArray = response.getJSONArray("meals")
                if (mealsArray.length() > 0) {
                    val mealObject = mealsArray.getJSONObject(0)
                    val recipe = Gson().fromJson(mealObject.toString(), Recipe::class.java)
                    _recipe.value = recipe
                }
            },
            { error ->
                // Handle error
            })

        Volley.newRequestQueue(getApplication<Application>().applicationContext).add(jsonObjectRequest)
    }
}
