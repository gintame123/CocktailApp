package com.example.cocktailapp.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import com.example.cocktailapp.cell.holder.ListAfficheCellHolder
import com.example.cocktailapp.classe.DrinkName
import com.example.cocktailapp.databinding.ListAfficheBoissonBinding
import com.example.cocktailapp.click.listener.ListAfficheCellClickListener

class ListAfficheAdapter (categoryList: ArrayList<DrinkName>, clickListener: ListAfficheCellClickListener) :
        RecyclerView.Adapter<ListAfficheCellHolder>() {

        var dataSource = categoryList

        private val delegate = clickListener

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAfficheCellHolder {
            val categoryListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.list_affiche_boisson,
                    parent,
                    false) as ListAfficheBoissonBinding
            return ListAfficheCellHolder(categoryListBinding)
        }

        override fun onBindViewHolder(holder: ListAfficheCellHolder, position: Int) {
            holder.bindItems(dataSource[position])
            holder.itemView.setOnClickListener {
                delegate.clickOnBoisson(dataSource[position])
            }

        }

        override fun getItemCount(): Int {
            return dataSource.size
        }
}