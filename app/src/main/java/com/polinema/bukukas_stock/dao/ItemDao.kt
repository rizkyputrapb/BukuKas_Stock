package com.polinema.bukukas_stock.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAllItem(): LiveData<List<Item>>

    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM item WHERE item_stock <= item_minimal")
    suspend fun getThinningItem(): List<Item>

    @Delete
    suspend fun delete(item: Item)

    @Query("UPDATE item SET item_stock = :item_stock, item_minimal = :item_minimal WHERE id = :id")
    suspend fun updateItem(item_stock: Int, item_minimal: Int, id: Int)
}