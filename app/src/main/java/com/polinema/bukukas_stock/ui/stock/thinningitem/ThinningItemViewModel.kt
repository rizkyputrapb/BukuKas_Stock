package com.polinema.bukukas_stock.ui.stock.thinningitem

import android.content.Context
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
class ThinningItemViewModel @Inject constructor(@ApplicationContext context: Context): ViewModel() {
    private val dbHelperImpl = DbHelperImpl(DbBuilder.getInstance(context))

    fun getThinningItem() = dbHelperImpl.getThinningItem()

    fun deleteItem(item: Item) = liveData(Dispatchers.Default) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(dbHelperImpl.delete(item)))
        } catch (e: Exception) {
            emit(
                Resource.error(
                null,
                e.message ?: "Unknown error"
            ))

        }
    }
}