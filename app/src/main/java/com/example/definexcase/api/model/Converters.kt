package com.example.definexcase.api.model

import androidx.room.TypeConverter
import com.example.definexcase.api.model.listResponse.OldPrice
import com.example.definexcase.api.model.listResponse.Price

class Converters {

    @TypeConverter
    fun fromPriceToValue(oldPrice: OldPrice?): Double? = oldPrice?.value

    @TypeConverter
    fun toOldPriceToValue(value: Double?): OldPrice? = value?.let { OldPrice("$", it) }

    @TypeConverter
    fun fromPriceToValue(price: Price): Double = price.value

    @TypeConverter
    fun toPriceToValue(value: Double): Price = Price("$", value)

}