package com.example.android_2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.android_2.R
import com.example.android_2.databinding.FragmentOnBoardBinding
import com.example.android_2.ui.adapters.OnBoardViewPagerAdapter
import com.example.android_2.utils.PreferenceHelper

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    private fun setupListener() = with(binding) {
        binding.btnNext.setOnClickListener {
            with(viewPager) {
                if (currentItem < 3) {
                    setCurrentItem(viewPager.currentItem + 1, true)
                }
            }
            binding.btnStart.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardFragment_to_noteAppFragment)
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.btnNext.isVisible = true
                        binding.btnStart.isVisible = false
                    }
                    1 -> {
                        binding.btnNext.isVisible = true
                        binding.btnStart.isVisible = false
                    }
                    2 -> {
                        binding.btnNext.isVisible = false
                        binding.btnStart.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
//    private fun open(){
//        PreferenceHelper.safeBoolean = true
//    }
}