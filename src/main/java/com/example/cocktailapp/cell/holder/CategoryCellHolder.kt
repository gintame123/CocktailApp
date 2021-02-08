package com.example.cocktailapp.cell.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.classe.Category
import com.example.cocktailapp.databinding.CategoryListCellBinding


class CategoryCellHolder(categoryListCellBinding: CategoryListCellBinding) : RecyclerView.ViewHolder(categoryListCellBinding.root) {
    private var binding = categoryListCellBinding

    
    fun bindItems(drink: Category){
       binding.name1.text = drink.name
    }
}


