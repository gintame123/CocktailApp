package com.example.cocktailapp.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import com.example.cocktailapp.cell.holder.CategoryCellHolder
import com.example.cocktailapp.classe.Category
import com.example.cocktailapp.databinding.CategoryListCellBinding
import com.example.cocktailapp.click.listener.CategoryCellClickListener


class CategoryListAdapter(categoryList: ArrayList<Category>, clickListener: CategoryCellClickListener) :
    RecyclerView.Adapter<CategoryCellHolder>() {
    var dataSource = categoryList

    private val delegate = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCellHolder {
        val categoryListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.category_list_cell,
            parent,
            false) as CategoryListCellBinding
        return CategoryCellHolder(categoryListBinding)
    }

    override fun onBindViewHolder(holder: CategoryCellHolder, position: Int) {
        holder.bindItems(dataSource[position])
        holder.itemView.setOnClickListener {
            delegate.clickOnCategory(dataSource[position])
        }

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}