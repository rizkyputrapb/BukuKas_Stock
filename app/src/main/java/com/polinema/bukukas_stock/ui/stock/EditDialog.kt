package com.polinema.bukukas_stock.ui.stock

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.githubuserdetailed.api.Status
import com.polinema.bukukas_stock.R
import com.polinema.bukukas_stock.dao.Item
import com.polinema.bukukas_stock.databinding.EditDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditDialog @Inject constructor(item: Item) : DialogFragment() {

    private val viewModel: StockViewModel by viewModels()
    lateinit var binding: EditDialogBinding
    lateinit var item: Item

    companion object {
        fun newInstance(item: Item): EditDialog {
            val editDialog = EditDialog(item)
            val args = Bundle()
            args.putParcelable("item", item)
            editDialog.arguments = args
            return editDialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments != null) {
            item = requireArguments().getParcelable("item")!!
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_dialog, container, false)
        binding.item = item
        binding.lifecycleOwner = this
        binding.btnCloseEdit.setOnClickListener {
            dismiss()
        }
        binding.edtEditItemAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty() || Integer.parseInt(p0!!.toString()) < 0) {
                    binding.edtEditItemAmount.setText("0")
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        binding.edtEditItemMinimal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty() || Integer.parseInt(p0!!.toString()) < 0) {
                    binding.edtEditItemMinimal.setText("0")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.btnEditAddAmount.setOnClickListener {
            val addition = Integer.parseInt(binding.edtEditItemAmount.text.toString()) + 1
            binding.edtEditItemAmount.setText(addition.toString())
        }
        binding.btnSubtractEditAmount.setOnClickListener {
            val subtract = Integer.parseInt(binding.edtEditItemAmount.text.toString()) - 1
            binding.edtEditItemAmount.setText(subtract.toString())
        }
        binding.btnEditAddMinimal.setOnClickListener {
            val addition = Integer.parseInt(binding.edtEditItemMinimal.text.toString()) + 1
            binding.edtEditItemMinimal.setText(addition.toString())
        }
        binding.btnSubtractEditMinimal.setOnClickListener {
            val subtract = Integer.parseInt(binding.edtEditItemMinimal.text.toString()) - 1
            binding.edtEditItemMinimal.setText(subtract.toString())
        }
        binding.btnSaveEdit.setOnClickListener {
            val newItem = Item(
                item_name = item.item_name,
                item_stock = Integer.parseInt(binding.edtEditItemAmount.text.toString()),
                item_minimal = Integer.parseInt(binding.edtEditItemMinimal.text.toString())
            )
            newItem.id = item.id
            viewModel.editItem(newItem)
                .observe(viewLifecycleOwner, {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.LOADING -> {

                            }
                            Status.SUCCESS -> {
                                Log.d("StockRoom", "editItem: Success")
                                Toast.makeText(
                                    activity?.applicationContext,
                                    "Item Edited",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            Status.ERROR -> {
                                Log.e("StockRoom", "editItem: Failed (${it.message})")
                                Toast.makeText(
                                    activity?.applicationContext,
                                    "Error editing item: ${it.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                })
            dismiss()
        }
        return binding.root
    }
}