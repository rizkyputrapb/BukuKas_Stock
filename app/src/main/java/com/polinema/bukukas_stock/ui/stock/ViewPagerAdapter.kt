package com.polinema.bukukas_stock.ui.stock

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(list: ArrayList<Fragment>, lifecycle: Lifecycle, fragment: FragmentManager) :
    FragmentStateAdapter(fragment, lifecycle) {

    private val fragmentList = list

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}