package com.polinema.bukukas_stock.ui.akun

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polinema.bukukas_stock.R

class AkunFragment : Fragment() {

    companion object {
        fun newInstance() = AkunFragment()
    }

    private lateinit var viewModel: AkunViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.akun_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AkunViewModel::class.java)
        // TODO: Use the ViewModel
    }

}