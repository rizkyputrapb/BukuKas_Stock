package com.polinema.bukukas_stock.dao

import androidx.lifecycle.LiveData

class DbHelperImpl(private val appDatabase: AppDatabase) : DbHelper {

    override fun getAllItem(): LiveData<List<Item>> = appDatabase.itemDao().getAllItem()

    override suspend fun insertItem(item: Item) = appDatabase.itemDao().insertItem(item)

    override fun getThinningItem(): LiveData<List<Item>> = appDatabase.itemDao().getThinningItem()

    override suspend fun delete(item: Item) = appDatabase.itemDao().delete(item)

    override suspend fun updateItem(item: Item) = appDatabase.itemDao().updateItem(item)
}