package com.polinema.bukukas_stock.ui.transaksi

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polinema.bukukas_stock.R

class TransaksiFragment : Fragment() {

    companion object {
        fun newInstance() = TransaksiFragment()
    }

    private lateinit var viewModel: TransaksiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transaksi_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}