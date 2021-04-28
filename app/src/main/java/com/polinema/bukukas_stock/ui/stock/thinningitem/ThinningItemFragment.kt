package com.polinema.bukukas_stock.ui.stock.thinningitem

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserdetailed.api.Status
import com.polinema.bukukas_stock.R
import com.polinema.bukukas_stock.dao.Item
import com.polinema.bukukas_stock.databinding.ThinningItemFragmentBinding
import com.polinema.bukukas_stock.ui.stock.AllItemAdapter
import com.polinema.bukukas_stock.ui.stock.EditDialog
import com.polinema.bukukas_stock.ui.stock.ItemOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThinningItemFragment : Fragment() {

    companion object {
        fun newInstance() = ThinningItemFragment()
    }

    private val viewModel: ThinningItemViewModel by viewModels()
    lateinit var binding: ThinningItemFragmentBinding
    lateinit var thinningAdapter: AllItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.thinning_item_fragment, container, false)
        binding.lifecycleOwner = this
        rvSetup()
        return binding.root
    }

    fun rvSetup() {
        viewModel.getThinningItem().observe(viewLifecycleOwner, { thinningList ->
            thinningAdapter =
                AllItemAdapter(thinningList as ArrayList<Item>, object : ItemOnClickListener {
                    override fun onBtnDeleteClick(item: Item) {
                        deleteDialog(item)
                    }

                    override fun onBtnEditClick(item: Item) {
                        val editDialog = EditDialog.newInstance(item)
                        editDialog.show(childFragmentManager, "item")
                    }

                })
            binding.thinningSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    thinningAdapter.filter.filter(p0)
                    return true
                }

            })
            with(binding.rvThinning) {
                adapter = thinningAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
            thinningAdapter.notifyItemChanged(0, thinningAdapter.itemCount)

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