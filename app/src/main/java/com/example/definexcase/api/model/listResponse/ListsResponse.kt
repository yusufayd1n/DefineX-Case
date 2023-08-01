package com.example.definexcase.api.model.listResponse


data class ListsResponse(
    val isSuccess: Boolean,
    val list: List<ListItems>,
    val message: String,
    val statusCode: String
)