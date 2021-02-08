package com.example.cocktailapp.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import com.example.cocktailapp.cell.holder.CocktailCellHolder
import com.example.cocktailapp.classe.DrinkName
import com.example.cocktailapp.databinding.CocktailListCellBinding
import com.example.cocktailapp.click.listener.CocktailCellClickListener

class CoktailListAdapter(cocktailList: ArrayList<DrinkName>, clickListener: CocktailCellClickListener) : RecyclerView.Adapter<CocktailCellHolder>() {

    private val dataSource = cocktailList
    private val delegate = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailCellHolder {
        val cocktailListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cocktail_list_cell, parent, false)
        as CocktailListCellBinding
        return CocktailCellHolder(cocktailListBinding)
    }

    override fun onBindViewHolder(holder: CocktailCellHolder, position: Int) {
        holder.bindItems(dataSource[position])
        holder.itemView.setOnClickListener{delegate.onClickCocktail(dataSource[position])}
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

}