package com.example.cocktailapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailapp.list.adapter.CategoryListAdapter
import com.example.cocktailapp.R
import com.example.cocktailapp.WebServiceInterface
import com.example.cocktailapp.classe.Category
import com.example.cocktailapp.classe.ListOfDrink
import com.example.cocktailapp.databinding.ListFragmentBinding
import com.example.cocktailapp.click.listener.CategoryCellClickListener
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.DriverManager
import java.sql.DriverManager.println
import java.util.*
import kotlin.collections.ArrayList

class ListFragment : Fragment(), CategoryCellClickListener {

    private lateinit var binding: ListFragmentBinding
    private var listAfficheCategory = ListAfficheCategory()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryRecyclerView.setHasFixedSize(true)

        val categoryListLayoutManager = LinearLayoutManager(context)
        binding.categoryRecyclerView.layoutManager = categoryListLayoutManager


        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val webServiceInterface = retrofit.create(WebServiceInterface::class.java)


        val category = webServiceInterface.getCategory("list")
        category.enqueue(object : Callback<ListOfDrink> {


            override fun onResponse(call: Call<ListOfDrink>, response: Response<ListOfDrink>) {
                val responseFromJson = response.body()
                val list = ArrayList<Category>()
                for (i in responseFromJson?.drinks!!){
                    list.add(Category(i.strCategory))
                    DriverManager.println(i.strCategory)
                }
                println(list)
                val adapter = CategoryListAdapter(list, this@ListFragment)
                binding.categoryRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<ListOfDrink>, t: Throwable) {
                println("Fail")
                println(t.message)
            }
        })
    }


    override fun clickOnCategory(drink: Category) {
        val cat = drink.name
        println(cat)
        listAfficheCategory.getRecupSearchAffiche(cat)
        this.onDetach()
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, listAfficheCategory)
                .commit()
    }
}