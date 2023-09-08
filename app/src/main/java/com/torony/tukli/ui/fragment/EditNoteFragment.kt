package com.torony.tukli.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.torony.tukli.R
import com.torony.tukli.databinding.FragmentEditNoteBinding
import com.torony.tukli.model.Notes
import com.torony.tukli.viewModel.NoteViewModel
import java.util.Date

class EditNoteFragment : Fragment() {

    private val note by navArgs<EditNoteFragmentArgs>()
    lateinit var binding: FragmentEditNoteBinding
    var priority: String = "1"
    val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.EditTitle.setText(note.data.title)
        binding.EditNote.setText(note.data.notes)

        when (note.data.priority) {
            "1" -> {
                priority = "1"
                binding.priorityGreen.setImageResource(R.drawable.baseline_done_24)
                binding.priorityRed.setImageResource(0)
                binding.priorityYellow.setImageResource(0)
            }

            "2" -> {
                priority = "2"
                binding.priorityYellow.setImageResource(R.drawable.baseline_done_24)
                binding.priorityRed.setImageResource(0)
                binding.priorityGreen.setImageResource(0)
            }

            "3" -> {
                priority = "3"
                binding.priorityRed.setImageResource(R.drawable.baseline_done_24)
                binding.priorityGreen.setImageResource(0)
                binding.priorityYellow.setImageResource(0)
            }
        }

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

        binding.btnEditeUpdateNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.EditTitle.text.toString()
        val notes = binding.EditNote.text.toString()
        val date = Date()
        val notesData: CharSequence = DateFormat.format("MMMM d, yyyy", date.time)
        val data = Notes(
            note.data.id,
            title = title,
            notes = notes,
            date = notesData.toString(),
            priority = priority
        )
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(), "Note updated successfully.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val yes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val no = bottomSheet.findViewById<TextView>(R.id.dialogNo)

            yes?.setOnClickListener {
                viewModel.deleteNotes(note.data.id!!)
                bottomSheet.dismiss()
                findNavController().popBackStack()
            }

            no?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }


}