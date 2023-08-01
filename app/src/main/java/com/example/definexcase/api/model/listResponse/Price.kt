package com.example.definexcase.api.model.listResponse

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Price(
    @ColumnInfo(name = "price_currency")
    val currency: String,
    @ColumnInfo(name = "price_value")
    val value: Double
)