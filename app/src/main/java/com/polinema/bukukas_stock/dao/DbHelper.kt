package com.polinema.bukukas_stock.dao

import androidx.lifecycle.LiveData

interface DbHelper {
    fun getAllItem(): LiveData<List<Item>>
    suspend fun insertItem(item: Item)
    suspend fun getThinningItem(): List<Item>
    suspend fun delete(item: Item)
    suspend fun updateItem(item_stock: Int, item_minimal: Int, id: Int)
}