package com.polinema.bukukas_stock.ui.stock.allitem

import com.polinema.bukukas_stock.dao.Item

interface ItemOnClickListener {
    fun onBtnDeleteClick(item: Item)
    fun onBtnEditClick(item_amount: Int, item_minimal: Int)
}