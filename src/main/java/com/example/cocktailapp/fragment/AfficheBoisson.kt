package com.example.cocktailapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cocktailapp.R
import com.example.cocktailapp.WebServiceInterface
import com.example.cocktailapp.classe.ListOfDrink
import com.example.cocktailapp.databinding.AfficheRechercheBinding
import com.example.cocktailapp.databinding.ResultSearchBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.DriverManager

class AfficheBoisson : Fragment() {

    private lateinit var binding : ResultSearchBinding
    private var urlId = ""
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.result_search, container, false)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val webServiceInterface = retrofit.create(WebServiceInterface::class.java)


        val random = webServiceInterface.getIdCocktail("lookup", urlId)
        random.enqueue(object : Callback<ListOfDrink> {
            override fun onResponse(call: Call<ListOfDrink>, response: Response<ListOfDrink>) {
                val responseFromJson = response.body()
                if (responseFromJson != null) {
                    println(responseFromJson.drinks[0].strDrink)
                }
                Picasso.get().load( responseFromJson?.drinks?.get(0)?.strDrinkThumb).into(binding.cokctailImageView)
                binding.cocktailNameView.text = responseFromJson?.drinks?.get(0)?.strDrink
                binding.categorieDrinkView.text = responseFromJson?.drinks?.get(0)?.strCategory
                binding.alcoholicOrNotView.text = responseFromJson?.drinks?.get(0)?.strAlcoholic
                binding.instructionView.text = responseFromJson?.drinks?.get(0)?.strInstructions
                binding.ingredientView1.text = responseFromJson?.drinks?.get(0)?.displayIngredient()
                binding.mesureView.text = responseFromJson?.drinks?.get(0)?.displayMeasure()
            }

            override fun onFailure(call: Call<ListOfDrink>, t: Throwable) {
                DriverManager.println("Fail")
                DriverManager.println(t.message)
            }

        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun getRecupSearchAffiche(a: String) {
        println("l'id de la boissoin : " + a)
        urlId = a
    }
}