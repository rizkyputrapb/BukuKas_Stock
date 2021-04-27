package com.polinema.bukukas_stock.ui.stock.allitem

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserdetailed.api.Status
import com.polinema.bukukas_stock.R
import com.polinema.bukukas_stock.dao.Item
import com.polinema.bukukas_stock.databinding.AllItemFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AllItemFragment : Fragment() {

    companion object {
        fun newInstance() = AllItemFragment()
    }

    private val viewModel: AllItemViewModel by viewModels()
    lateinit var binding: AllItemFragmentBinding
    lateinit var itemAdapter: AllItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.all_item_fragment, container, false)
        binding.lifecycleOwner = this
        rvSetup()
        binding.itemSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                itemAdapter.filter.filter(p0)
                return true
            }

        })
        return binding.root
    }

    fun rvSetup() {
        viewModel.getAllItem().observe(viewLifecycleOwner, { listItems ->
            itemAdapter = AllItemAdapter(
                listItems as ArrayList<Item>,
                object : ItemOnClickListener {
                    override fun onBtnDeleteClick(item: Item) {
                        deleteDialog(item)
                    }

                    override fun onBtnEditClick(item_amount: Int, item_minimal: Int) {
                        TODO("Not yet implemented")
                    }


                })
            with(binding.rvAllItem) {
                adapter = itemAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
            itemAdapter.notifyItemRangeChanged(0, itemAdapter.itemCount)
        })

    }

    fun deleteDialog(item: Item) {
        AlertDialog.Builder(context)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(R.string.delete_title).setMessage(R.string.delete_message)
            .setPositiveButton(R.string.delete_confirm) { dialog, which ->
                viewModel.deleteItem(item).observe(viewLifecycleOwner, {
                    when (it.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(
                                context,
                                "Barang ${item.item_name} berhasil dihapus",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Barang ${item.item_name} gagal dihapus",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
            }.setNegativeButton(R.string.delete_cancel, null).show()
    }

}