package com.example.cocktailapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cocktailapp.databinding.ActivityMainBinding
import com.example.cocktailapp.fragment.ListFragment
import com.example.cocktailapp.fragment.RandomFragment
import com.example.cocktailapp.fragment.ResultSearchFragment
import com.example.cocktailapp.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    val randomFragment = RandomFragment()
    val listFragment = ListFragment()
    val searchFragment = SearchFragment()
    val resultSearchFragment = ResultSearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigationView2.setOnNavigationItemSelectedListener(this)
        loadFragment(randomFragment)

    }

    private fun loadFragment(fragment: Fragment) : Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_random) {
            return loadFragment(randomFragment)
        }
        if (item.itemId == R.id.action_list) {
            return loadFragment(listFragment)
        }
        if (item.itemId == R.id.action_search) {
            return loadFragment(searchFragment)
        }
        return false
    }

    fun onClickSearch(view: View){
        val recherche = this.findViewById(R.id.searchText) as EditText
        val values = recherche.text.toString()
        val bundle = Bundle()
        bundle.putString("nameCocktail", values)
        resultSearchFragment.arguments = bundle
        println(values)
        loadFragment(resultSearchFragment)
    }
}

