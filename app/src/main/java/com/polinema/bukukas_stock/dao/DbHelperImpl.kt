package com.polinema.bukukas_stock.dao

import androidx.lifecycle.LiveData

class DbHelperImpl(private val appDatabase: AppDatabase) : DbHelper {

    override suspend fun getAllItem(): LiveData<List<Item>> = appDatabase.itemDao().getAllItem()

    override suspend fun insertItem(item: Item) = appDatabase.itemDao().insertItem(item)

    override suspend fun getThinningItem(): List<Item> = appDatabase.itemDao().getThinningItem()

    override suspend fun delete(item: Item) = appDatabase.itemDao().delete(item)

    override suspend fun updateItem(item_stock: Int, item_minimal: Int, id: Int) = appDatabase.itemDao().updateItem(item_stock, item_minimal, id)
}