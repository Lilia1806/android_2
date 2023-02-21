package com.example.android_2.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_2.App
import com.example.android_2.databinding.FragmentDetailBinding
import com.example.android_2.ui.adapters.NoteModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
    }

    private fun sendData() = with(binding) {
        bottom.setOnClickListener {
            if (nam.text.isNotEmpty() && tex.text.isNotEmpty()) {
                val title = nam.text.toString()
                val description = tex.text.toString()
                val data = date.text.toString()
                App().getInstance()?.noteDao()?.insert(NoteModel(title, description, data))

                findNavController().navigateUp()
            }
        }
    }
}