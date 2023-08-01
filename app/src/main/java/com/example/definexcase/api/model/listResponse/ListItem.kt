package com.example.definexcase.api.model.listResponse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.definexcase.api.model.Converters
import com.google.gson.annotations.SerializedName

@Entity
data class ListItems(
    @ColumnInfo(name = "description_name")
    val description: String,
    @ColumnInfo(name = "discount_name")
    val discount: String,
    @ColumnInfo(name = "image_url")
    @SerializedName("imageUrl")
    val photoUrl: String,
    @ColumnInfo(name = "old_price")
    @field:TypeConverters(Converters::class)
    val oldPrice: OldPrice?,
    @ColumnInfo(name = "price_name")
    @field:TypeConverters(Converters::class)
    val price: Price,
    @ColumnInfo(name = "rate_Percentage")
    val ratePercentage: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
    var listId: Int = 0
}