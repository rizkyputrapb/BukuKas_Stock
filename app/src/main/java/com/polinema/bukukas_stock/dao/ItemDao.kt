package com.polinema.bukukas_stock.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAllItem(): LiveData<List<Item>>

    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM item WHERE item_stock <= item_minimal")
    fun getThinningItem(): LiveData<List<Item>>

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun updateItem(item: Item)
}