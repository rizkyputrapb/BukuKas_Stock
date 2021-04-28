package com.polinema.bukukas_stock.ui.stock

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.polinema.bukukas_stock.dao.Item
import com.polinema.bukukas_stock.databinding.ItemStocklistBinding
import java.util.*

class AllItemAdapter(private var itemList: ArrayList<Item>, onClickListener: ItemOnClickListener) :
    RecyclerView.Adapter<AllItemAdapter.AllItemViewHolder>(), Filterable {
    var itemFilterList = ArrayList<Item>()
    private var onItemClickListener = onClickListener

    init {
        itemFilterList = itemList
    }

    class AllItemViewHolder(private val binding: ItemStocklistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item?, onItemClickListener: ItemOnClickListener) {
            binding.item = item
            binding.clicklistener = onItemClickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStocklistBinding.inflate(layoutInflater, parent, false)
        return AllItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllItemViewHolder, position: Int) {
        val item = itemFilterList?.get(position)
        holder.bind(item, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return itemFilterList?.size ?: 0
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Log.d("search", "Query: $charSearch")
                itemFilterList = if (charSearch.isEmpty()) {
                    itemList
                } else {
                    val resultList = ArrayList<Item>()
                    for (row in itemList) {
                        if (row.item_name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemFilterList = results?.values as ArrayList<Item>
                notifyDataSetChanged()
            }

        }
    }
}