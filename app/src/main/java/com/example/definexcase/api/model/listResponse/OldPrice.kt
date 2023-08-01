package com.example.definexcase.api.model.listResponse

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class OldPrice(
    @ColumnInfo(name = "oldPrice_currency")
    val currency: String,
    @ColumnInfo(name = "oldPrice_value")
    val value: Double
)