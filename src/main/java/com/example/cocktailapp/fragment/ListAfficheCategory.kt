package com.example.cocktailapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailapp.R
import com.example.cocktailapp.WebServiceInterface
import com.example.cocktailapp.classe.DrinkName
import com.example.cocktailapp.classe.ListOfDrink
import com.example.cocktailapp.databinding.ListCocktailByNameBinding
import com.example.cocktailapp.click.listener.ListAfficheCellClickListener
import com.example.cocktailapp.databinding.ListAfficheByBoissonBinding
import com.example.cocktailapp.list.adapter.ListAfficheAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListAfficheCategory: Fragment(), ListAfficheCellClickListener {

    private lateinit var binding : ListAfficheByBoissonBinding
    private val afficheBoisson = AfficheBoisson()
    private var cat = ""
    private var idBoisson = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.list_affiche_by_boisson,container,false)
        println("dnjn")
        binding.cocktailListRecyclerView.setHasFixedSize(true)

        val categoryListLayoutManager = LinearLayoutManager(context)
        binding.cocktailListRecyclerView.layoutManager = categoryListLayoutManager


        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val webServiceInterface = retrofit.create(WebServiceInterface::class.java)


        val search = webServiceInterface.getCategoryFilter(cat)
        search.enqueue(object : Callback<ListOfDrink> {
            override fun onResponse(call: Call<ListOfDrink>, response: Response<ListOfDrink>) {
                val responseFromJson = response.body()
                val list = ArrayList<DrinkName>()
                for (i in responseFromJson?.drinks!!){
                    val cate = i.strCategory
                    list.add(DrinkName(i.strDrink, "", i.strDrinkThumb, i.idDrink))
                }
                for (j in list){
                    println(j.name)
                }
                val adapter = ListAfficheAdapter(list, this@ListAfficheCategory )
                binding.cocktailListRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<ListOfDrink>, t: Throwable) {
                println("Fail " + t.message)
            }

        })

        return binding.root
    }



    fun getRecupSearchAffiche(id: String) {
        println("la categorie : " + id)
        cat = id
    }

    override fun clickOnBoisson(drink: DrinkName) {
        val id = drink.id
        afficheBoisson.getRecupSearchAffiche(id)
        this.onDetach()
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, afficheBoisson)
                .commit()
    }
}