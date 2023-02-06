package com.example.android_2.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_2.databinding.FragmentNoteAppBinding

class NoteAppFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteAppBinding.inflate(inflater, container, false)
        return binding.root
    }
}