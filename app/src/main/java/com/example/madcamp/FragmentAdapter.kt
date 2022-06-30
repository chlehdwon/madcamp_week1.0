package com.example.madcamp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
@Suppress("DEPRECATION")


class FragmentAdapter (fm : FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val fragment =  when(position)
        {
            0-> Fragment1()
            1-> Fragment2()
            2-> Fragment3()
            else -> getItem(position)
        }
        return fragment
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            0->"Contact"
            1->"Images"
            2->"Special"
            else -> "main"
        }
        return title     }
}