package com.example.android_2.ui.fragments.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.android_2.App
import com.example.android_2.R
import com.example.android_2.databinding.FragmentDetailBinding
import com.example.android_2.ui.adapters.NoteModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var backgroundColor = "#1E1E1E"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {

        btnImage.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_noteAppFragment)
        }
        binding.btnBlack.setOnClickListener {
            backgroundColor = "#1E1E1E"
        }
        binding.btnWhite.setOnClickListener {
            backgroundColor = "#EBE4C9"
        }
        binding.btnRed.setOnClickListener {
            backgroundColor = "#571818"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendData() = with(binding) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM")
        val formatted = current.format(formatter)
        binding.date.text = formatted

        bottom.setOnClickListener {
            if (nam.text.isNotEmpty() && tex.text.isNotEmpty()) {
                val title = nam.text.toString()
                val description = tex.text.toString()
                val time = time.text.toString()
                val data = date.text.toString()

                App().getInstance()?.noteDao()?.insert(NoteModel(title, description, time, data, color = backgroundColor))

                findNavController().navigateUp()
            }
        }
    }
}