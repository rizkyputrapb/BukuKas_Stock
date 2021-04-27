package com.polinema.bukukas_stock.ui.stock.thinningitem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polinema.bukukas_stock.R

class ThinningItemFragment : Fragment() {

    companion object {
        fun newInstance() = ThinningItemFragment()
    }

    private lateinit var viewModel: ThinningItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.thinning_item_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThinningItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

}