package com.example.cocktailapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailapp.list.adapter.CoktailListAdapter
import com.example.cocktailapp.R
import com.example.cocktailapp.WebServiceInterface
import com.example.cocktailapp.classe.DrinkName
import com.example.cocktailapp.classe.ListOfDrink
import com.example.cocktailapp.databinding.ListCocktailByNameBinding
import com.example.cocktailapp.click.listener.CocktailCellClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ResultSearchFragment : Fragment(), CocktailCellClickListener {

    private lateinit var binding : ListCocktailByNameBinding
    private var searchAffiche = SearchAffiche()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_cocktail_by_name, container, false)

        binding.cocktailListRecyclerView.setHasFixedSize(true)

        val categoryListLayoutManager = LinearLayoutManager(context)
        binding.cocktailListRecyclerView.layoutManager = categoryListLayoutManager

        val valuesFromIntent = arguments?.getString("nameCocktail")
        println(valuesFromIntent)
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val webServiceInterface = retrofit.create(WebServiceInterface::class.java)

        val search = webServiceInterface.getSearch(valuesFromIntent)
        search.enqueue(object : Callback<ListOfDrink>{
            override fun onResponse(call: Call<ListOfDrink>, response: Response<ListOfDrink>) {
                val responseFromJson = response.body()
                if (responseFromJson != null) {
                    val liste = ArrayList<DrinkName>()
                    for (i in responseFromJson.drinks!!){
                        liste.add(DrinkName(i.strDrink, i.strCategory, i.strDrinkThumb, i.idDrink))
                    }
                    val adaptere = CoktailListAdapter(liste, this@ResultSearchFragment )
                    binding.cocktailListRecyclerView.adapter = adaptere
                }


            }

            override fun onFailure(call: Call<ListOfDrink>, t: Throwable) {
                println("Fail " + t.message)
            }

        })

        return binding.root
    }

    override fun onClickCocktail(listOfDrink: DrinkName) {
        val id = listOfDrink.id
        searchAffiche.getRecupSearchAffiche(id)
        this.onDetach()
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, searchAffiche)
                .commit()
    }

}