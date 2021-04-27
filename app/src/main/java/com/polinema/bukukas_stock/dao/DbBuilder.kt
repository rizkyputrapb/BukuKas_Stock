package com.polinema.bukukas_stock.dao

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext

object DbBuilder {
    private var INSTANCE: AppDatabase? = null

    fun getInstance(@ApplicationContext appContext: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(appContext)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "Item"
        ).build()
}