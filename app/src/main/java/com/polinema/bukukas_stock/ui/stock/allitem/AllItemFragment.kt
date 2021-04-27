package com.polinema.bukukas_stock.ui.stock.allitem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserdetailed.api.Resource
import com.example.githubuserdetailed.api.Status
import com.polinema.bukukas_stock.R
import com.polinema.bukukas_stock.databinding.AllItemFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

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
        observerSertup()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSetup()
    }

    fun rvSetup() {
        itemAdapter = AllItemAdapter()
        with(binding.rvAllItem) {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

    }

    fun observerSertup() {
        viewModel.getAllItem().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    it.data.let { listItems ->
                        itemAdapter.itemList = listItems?.value
                        itemAdapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(
                        activity?.applicationContext,
                        "Error getting item: ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

}