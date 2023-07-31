package com.example.definexcase.api.model.listResponse

import androidx.room.PrimaryKey

data class ListItems(
    val description: String,
    val discount: String,
    val imageUrl: String,
    val oldPrice: OldPrice,
    val price: Price,
    val ratePercentage: String?,
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
)