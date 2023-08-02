package com.example.firstproject

data class CarData(
    val title: String,
    val price: Double,
    var pic: Int? = null,
    var isExpandable: Boolean = false,
    val rating : Int = 0
)

