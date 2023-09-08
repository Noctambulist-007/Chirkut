package com.torony.tukli.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.torony.tukli.database.NotesDatabase
import com.torony.tukli.model.Notes
import com.torony.tukli.repository.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val repository: NoteRepository

    init {
        val dao = NotesDatabase.getDatabaseInstanse(application).myNotes()
        repository = NoteRepository(dao)
    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()


    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()

    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }
}