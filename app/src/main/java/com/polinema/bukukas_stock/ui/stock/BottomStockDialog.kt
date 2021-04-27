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
import androidx.fragment.app.viewModels
import com.example.githubuserdetailed.api.Status
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.polinema.bukukas_stock.R
import com.polinema.bukukas_stock.databinding.BottomSheetLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BottomStockDialog @Inject constructor() : BottomSheetDialogFragment() {

    private val viewModel: StockViewModel by viewModels()
    lateinit var binding: BottomSheetLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_layout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCloseSheet.setOnClickListener {
            dismiss()
        }
        binding.edtAmountItem.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                if (cs.isEmpty()) {
                    binding.edtAmountItem.setText("0")
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        binding.edtMinimalItem.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                if (cs.isEmpty() || Integer.parseInt(cs.toString()) < 0) {
                    binding.edtMinimalItem.setText("0")
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        binding.btnAddAmountItem.setOnClickListener {
            val addition = Integer.parseInt(binding.edtAmountItem.text.toString()) + 1
            binding.edtAmountItem.setText(addition.toString())
        }
        binding.btnSubstractAmountItem.setOnClickListener {
            if (Integer.parseInt(binding.edtAmountItem.text.toString()) > 0) {
                val substract = Integer.parseInt(binding.edtAmountItem.text.toString()) - 1
                binding.edtAmountItem.setText(substract.toString())
            }
        }
        binding.btnAddMinimalItem.setOnClickListener {
            val addition = Integer.parseInt(binding.edtMinimalItem.text.toString()) + 1
            binding.edtMinimalItem.setText(addition.toString())
        }
        binding.btnSubstractMinimalItem.setOnClickListener {
            if (Integer.parseInt(binding.edtMinimalItem.text.toString()) > 0) {
                val substract = Integer.parseInt(binding.edtMinimalItem.text.toString()) - 1
                binding.edtMinimalItem.setText(substract.toString())
            }
        }

        binding.btnAddItemtoStock.setOnClickListener {
            viewModel.addItem(
                itemName = binding.edtItemName.text.toString(),
                itemAmount = Integer.parseInt(binding.edtAmountItem.text.toString()),
                itemMinimal = Integer.parseInt(binding.edtMinimalItem.text.toString())
            ).observe(viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            Log.d("StockRoom", "addItem: Success")
                            Toast.makeText(
                                activity?.applicationContext,
                                "Added to Stock",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        Status.ERROR -> {
                            Log.e("StockRoom", "addItem: Failed (${it.message})")
                            Toast.makeText(
                                activity?.applicationContext,
                                "Error adding to Favorites: ${it.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            })
        }
        super.onViewCreated(view, savedInstanceState)
    }
}