package com.example.definexcase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.definexcase.api.model.Converters
import com.example.definexcase.api.model.listResponse.ListItems

@Database(entities = [ListItems::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductsDataBase() : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}