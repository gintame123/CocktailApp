package com.example.cocktailapp.cell.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.classe.DrinkName
import com.example.cocktailapp.databinding.CocktailListCellBinding
import com.squareup.picasso.Picasso

class CocktailCellHolder(cocktailListCellBinding: CocktailListCellBinding) : RecyclerView.ViewHolder(cocktailListCellBinding.root) {

    private val binding = cocktailListCellBinding

    fun bindItems(drink: DrinkName) {
        binding.nameCocktail.text = drink.name
        binding.alcoholicOrNot.text = drink.alcool
        Picasso.get().load(drink.image).into(binding.cocktalImage)
    }
}