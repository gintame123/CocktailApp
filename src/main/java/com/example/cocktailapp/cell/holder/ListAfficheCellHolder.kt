package com.example.cocktailapp.cell.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.classe.DrinkName
import com.example.cocktailapp.databinding.ListAfficheBoissonBinding
import com.squareup.picasso.Picasso

class ListAfficheCellHolder(listAfficheBoissonBinding: ListAfficheBoissonBinding) : RecyclerView.ViewHolder(listAfficheBoissonBinding.root) {
    private val binding = listAfficheBoissonBinding

    fun bindItems(drink: DrinkName) {
        binding.nameCocktail.text = drink.name
        Picasso.get().load(drink.image).into(binding.cocktalImage)
    }
}