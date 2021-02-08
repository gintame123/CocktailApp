package com.example.cocktailapp.classe

import com.google.gson.annotations.SerializedName

class ListOfDrink {

    @SerializedName("drinks")
    val drinks : List<Drinks> = ArrayList()
}

