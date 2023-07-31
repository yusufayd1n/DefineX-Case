package com.example.definexcase.api.model.listResponse

import androidx.room.Entity

@Entity
data class ListsResponse(
    val isSuccess: Boolean,
    val list: List<ListItems>,
    val message: String,
    val statusCode: String
)