package com.example.cocktailapp

import com.example.cocktailapp.classe.ListOfDrink
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceInterface {
    @GET("api/json/v1/1/random.php")
    fun getRandom() : Call<ListOfDrink>

    @GET("api/json/v1/1/list.php")
    fun getCategory(@Query("c") c: String) : Call<ListOfDrink>

    @GET("api/json/v1/1/search.php")
    fun getSearch(@Query("s") s: String?) : Call<ListOfDrink>

    @GET("api/json/v1/1/lookup.php")
    fun getIdCocktail(@Query("a") a: String?, @Query("i") i: String) : Call<ListOfDrink>

    @GET("api/json/v1/1/filter.php")
    fun getCategoryFilter(@Query("c") c: String) : Call<ListOfDrink>
}