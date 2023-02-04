package com.example.android_2.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_2.ui.fragment.OnBoardPagingFragment
import com.example.android_2.ui.fragment.OnBoardPagingFragment.Companion.ARG_ONBOARD_PAGE_POSITION

class OnBoardViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = OnBoardPagingFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_PAGE_POSITION, position)
        }
    }
}