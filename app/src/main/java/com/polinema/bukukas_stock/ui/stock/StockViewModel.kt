package com.polinema.bukukas_stock.ui.stock

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.githubuserdetailed.api.Resource
import com.polinema.bukukas_stock.dao.DbBuilder
import com.polinema.bukukas_stock.dao.DbHelperImpl
import com.polinema.bukukas_stock.dao.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val dbHelperImpl = DbHelperImpl(DbBuilder.getInstance(context))

    fun addItem(itemName: String, itemAmount: Int, itemMinimal: Int) =
        liveData(Dispatchers.Default) {
            val item =
                Item(item_name = itemName, item_stock = itemAmount, item_minimal = itemMinimal)
            emit(Resource.loading(null))
            try {
                emit(Resource.success(dbHelperImpl.insertItem(item)))

            } catch (e: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = e.message ?: "Unknown Error Occured!"
                    )
                )
            }
        }

    fun editItem(item: Item) = liveData(Dispatchers.Default) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(dbHelperImpl.updateItem(item)))
        } catch (e: Exception) {
            emit(
                Resource.error(
                    null,
                    e.message ?: "Unknown error"
                )
            )
            Log.i("updateItem", "update itemid: ${item.id} failed (${e.message})")
        }
    }
}