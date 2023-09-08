package com.torony.tukli.ui.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.torony.tukli.R
import com.torony.tukli.databinding.ItemNotesBinding
import com.torony.tukli.model.Notes
import com.torony.tukli.ui.fragment.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.notesTitle.text = notesList[position].title
        holder.binding.notesDate.text = notesList[position].date
        holder.binding.notesDescription.text = notesList[position].notes

        when (notesList[position].priority) {
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }

            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }

            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.light_green_dot)
            }
        }

        holder.binding.root.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(notesList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

}