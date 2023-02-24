package com.example.android_2.ui.fragments.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_2.App
import com.example.android_2.OnClickItem
import com.example.android_2.R
import com.example.android_2.databinding.FragmentNoteAppBinding
import com.example.android_2.ui.adapters.NoteAppAdapter
import com.example.android_2.ui.adapters.NoteModel
import com.example.android_2.utils.PreferenceHelper

class NoteAppFragment() : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteAppBinding
    private val list = ArrayList<NoteModel>()
    private var noteAppAdapter = NoteAppAdapter(list, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        setList()

    }

    private fun initialize() {
        binding.action.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAppAdapter
        }
    }

    private fun setupListener() {
        binding.btnAction.setOnClickListener {
            findNavController().navigate(R.id.action_noteAppFragment_to_detailFragment)
        }
        binding.grid.setOnClickListener {
            PreferenceHelper.safeBoolean = false
            binding.action.layoutManager = GridLayoutManager(requireContext() ,2)
            binding.grid.isVisible = false
            binding.linear.isVisible = true
        }
        binding.linear.setOnClickListener {
            PreferenceHelper.safeBoolean = true
            binding.action.layoutManager = LinearLayoutManager(requireContext())
            binding.grid.isVisible = true
            binding.linear.isVisible = false
        }
    }

    private fun setList() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAppAdapter.setList(it)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить?")
            setPositiveButton("Да", DialogInterface.OnClickListener { dialog, which ->
                App.appDatabase?.noteDao()?.delete(noteModel)
            })
            setNegativeButton("Нет", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            show()
        }
        builder.create()
    }
}