package com.polinema.bukukas_stock.dao

import androidx.lifecycle.LiveData

interface DbHelper {
    fun getAllItem(): LiveData<List<Item>>
    suspend fun insertItem(item: Item)
    fun getThinningItem(): LiveData<List<Item>>
    suspend fun delete(item: Item)
    suspend fun updateItem(item: Item)
}