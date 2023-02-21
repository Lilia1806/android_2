package com.example.android_2.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2.OnClickItem
import com.example.android_2.databinding.ItemBinding

class NoteAppAdapter(

    private var list: ArrayList<NoteModel>,
    private val onLongClickItem: OnClickItem
)

    : RecyclerView.Adapter<NoteAppAdapter.NoteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>) {
        this.list = list as ArrayList<NoteModel>
        notifyDataSetChanged()
    }

    class NoteViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(noteModel: NoteModel) {
            binding.txtName.text = noteModel.title
            binding.txtText.text = noteModel.description
            binding.date.text = noteModel.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnLongClickListener {
            onLongClickItem.onLongClick(list[position])
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}