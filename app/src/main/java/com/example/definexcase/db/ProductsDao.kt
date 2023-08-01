package com.example.definexcase.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.definexcase.api.model.listResponse.ListItems

@Dao
interface ProductsDao {
    @Insert
    suspend fun insertAll(vararg products: ListItems): List<Long>

    @Query("SELECT * FROM ListItems")
    suspend fun getAllProducts(): List<ListItems>

    @Query("SELECT * FROM ListItems WHERE listId =:listId")
    suspend fun getAllProductsWithListId(listId: Int): List<ListItems>

    @Query("DELETE FROM ListItems")
    suspend fun deleteAll()
}