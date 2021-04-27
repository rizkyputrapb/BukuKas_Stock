package com.polinema.bukukas_stock.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Item(
    @ColumnInfo(name = "item_name") var item_name: String?,
    @ColumnInfo(name = "item_stock") var item_stock: Int?,
    @ColumnInfo(name = "item_minimal") var item_minimal: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
