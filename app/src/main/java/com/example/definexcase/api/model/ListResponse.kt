package com.example.definexcase.api.model

data class ListResponse(
    val isSuccess: Boolean,
    val list: List<items>,
    val message: String,
    val statusCode: String
) {
    data class items(
        val description: String,
        val discount: String = "",
        val imageUrl: String,
        val oldPrice: OldPrice?,
        val price: Price,
        val ratePercentage: String? = ""
    ) {
        data class OldPrice(
            val currency: String,
            val value: Double
        )

        data class Price(
            val currency: String,
            val value: Double
        )
    }
}