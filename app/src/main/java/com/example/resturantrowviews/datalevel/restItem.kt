package com.example.resturantrowviews.datalevel

data class restItem (
    val id : Int = 0,
    val name : String = "hi",
    val item : List<Item> = emptyList(),
    val status: Boolean = false
)