package com.example.definexcase.api.model

import androidx.room.TypeConverter
import com.example.definexcase.api.model.listResponse.OldPrice
import com.example.definexcase.api.model.listResponse.Price

class Converters {
    @TypeConverter
    fun fromPriceToCurrency(oldPrice: OldPrice): String = oldPrice.currency

    @TypeConverter
    fun fromPriceToValue(oldPrice: OldPrice): Double = oldPrice.value

    @TypeConverter
    fun toOldPriceToCurrency(currency: String): OldPrice = OldPrice(currency, 0.0)

    @TypeConverter
    fun toOldPriceToValue(value: Double): OldPrice = OldPrice("", value)

    @TypeConverter
    fun fromPriceToCurrency(price: Price): String = price.currency

    @TypeConverter
    fun fromPriceToValue(price: Price): Double = price.value

    @TypeConverter
    fun toPriceToCurrency(currency: String): Price = Price(currency, 0.0)

    @TypeConverter
    fun toPriceToValue(value: Double): Price = Price("", value)

}