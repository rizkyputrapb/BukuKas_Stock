package com.polinema.bukukas_stock.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun itemDao(): ItemDao
}