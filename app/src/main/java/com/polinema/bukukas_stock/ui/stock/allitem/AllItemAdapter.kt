package com.polinema.bukukas_stock.ui.stock.allitem

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polinema.bukukas_stock.dao.Item
import com.polinema.bukukas_stock.databinding.ItemStocklistBinding

class AllItemAdapter : RecyclerView.Adapter<AllItemAdapter.AllItemViewHolder>() {
    var itemList: List<Item>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
            Log.i("ItemList", "List size: ${field?.size}")
        }

    class AllItemViewHolder(private val binding: ItemStocklistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item?) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStocklistBinding.inflate(layoutInflater, parent, false)
        return AllItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllItemViewHolder, position: Int) {
        val item = itemList?.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }
}