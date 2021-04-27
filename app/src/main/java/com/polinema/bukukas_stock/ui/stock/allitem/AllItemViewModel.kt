package com.polinema.bukukas_stock.ui.stock.allitem

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
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AllItemViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {
    private val dbHelperImpl = DbHelperImpl(DbBuilder.getInstance(context))

    fun getAllItem() = dbHelperImpl.getAllItem()

    fun deleteItem(item: Item) = liveData(Dispatchers.Default) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(dbHelperImpl.delete(item)))
        } catch (e: Exception) {
            emit(Resource.error(
                null,
                e.message ?: "Unknown error"
            ))

        }
    }
}