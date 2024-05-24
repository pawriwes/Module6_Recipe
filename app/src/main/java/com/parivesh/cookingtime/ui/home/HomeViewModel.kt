package com.parivesh.cookingtime.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.parivesh.cookingtime.Model.Recipe
import org.json.JSONException
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.reflect.TypeToken

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val recipesLiveData = MutableLiveData<List<Recipe>>()

    init {
        fetchRecipes()
    }

    fun getRecipes(): LiveData<List<Recipe>> {
        return recipesLiveData
    }

    private fun fetchRecipes() {
        val queue = Volley.newRequestQueue(getApplication<Application>().applicationContext)
        val url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val mealsArray = response.getJSONArray("meals")
                val gson = Gson()
                val listType = object : TypeToken<List<Recipe>>() {}.type
                val recipes: List<Recipe> = gson.fromJson(mealsArray.toString(), listType)
                recipesLiveData.value = recipes
            },
            { error ->
                // Handle error
            })

        queue.add(jsonObjectRequest)
    }
}