package com.torony.tukli.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.torony.tukli.R
import com.torony.tukli.databinding.FragmentCreateNoteBinding
import com.torony.tukli.model.Notes
import com.torony.tukli.viewModel.NoteViewModel
import java.util.Date

class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        binding.priorityGreen.setOnClickListener {
            binding.priorityGreen.setImageResource(R.drawable.baseline_done_24)
            binding.priorityRed.setImageResource(0)
            binding.priorityYellow.setImageResource(0)
            priority = "1"
        }

        binding.priorityYellow.setOnClickListener {
            binding.priorityYellow.setImageResource(R.drawable.baseline_done_24)
            binding.priorityRed.setImageResource(0)
            binding.priorityGreen.setImageResource(0)
            priority = "2"
        }

        binding.priorityRed.setOnClickListener {
            binding.priorityRed.setImageResource(R.drawable.baseline_done_24)
            binding.priorityGreen.setImageResource(0)
            binding.priorityYellow.setImageResource(0)
            priority = "3"
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.btnEditTitle.text.toString()
        val notes = binding.btnEditNote.text.toString()
        val date = Date()
        val notesData: CharSequence = DateFormat.format("MMMM d, yyyy", date.time)
        val data = Notes(
            null,
            title = title,
            notes = notes,
            date = notesData.toString(),
            priority = priority
        )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Note created successfully.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }
}