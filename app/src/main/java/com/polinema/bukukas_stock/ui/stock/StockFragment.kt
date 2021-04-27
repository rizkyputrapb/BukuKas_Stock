package com.polinema.bukukas_stock.ui.stock

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.polinema.bukukas_stock.R
import com.polinema.bukukas_stock.databinding.BottomSheetLayoutBinding
import com.polinema.bukukas_stock.databinding.StockFragmentBinding
import com.polinema.bukukas_stock.ui.stock.allitem.AllItemFragment
import com.polinema.bukukas_stock.ui.stock.thinningitem.ThinningItemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockFragment : Fragment() {

    companion object {
        fun newInstance() = StockFragment()
    }

    lateinit var binding: StockFragmentBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.stock_fragment, container, false)
        tabSetup()
        fabSetup()
        return binding.root
    }

    fun fabSetup() {
        binding.fabStock.setOnClickListener {
            BottomStockDialog().show(childFragmentManager, "BottomSheet")
        }
    }

    private fun tabSetup() {
        val titles = arrayOf(
            activity?.applicationContext?.resources?.getString(R.string.label_semua),
            activity?.applicationContext?.resources?.getString(R.string.label_menipis)
        )
        val fragmentList = arrayListOf<Fragment>(
            AllItemFragment(),
            ThinningItemFragment()
        )
        val tabLayout = binding.tabLayout
        viewPagerAdapter =
            ViewPagerAdapter(fragmentList, lifecycle, childFragmentManager)
        binding.allitemViewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, binding.allitemViewPager) { tab, position ->
            tab.text = titles[position]
            binding.allitemViewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

}