package com.example.android_2.ui.fragments.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_2.R
import com.example.android_2.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_PAGE_POSITION)) {
            0 -> {
                frText.text = "Очень удобный функционал"
                lottieAnimation.setAnimation(R.raw.json)
            }
            1 -> {

                frText.text = "Быстрый, качественный продукт"
                lottieAnimation.setAnimation(R.raw.lottie2)
            }
            2 -> {
                frText.text = "Куча функций и интересных фишек"
                lottieAnimation.setAnimation(R.raw.lottie3)
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_PAGE_POSITION = "onBoardPage"
    }
}