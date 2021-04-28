package com.polinema.bukukas_stock.ui.stock

import com.polinema.bukukas_stock.dao.Item

interface ItemOnClickListener {
    fun onBtnDeleteClick(item: Item)
    fun onBtnEditClick(item: Item)
}