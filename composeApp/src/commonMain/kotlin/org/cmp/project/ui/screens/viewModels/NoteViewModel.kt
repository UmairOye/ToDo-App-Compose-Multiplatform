package org.cmp.project.ui.screens.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.cmp.project.data.repository.NotesRepository
import org.cmp.project.data.sqldelight.NoteEntity
import org.cmp.project.data.sqldelight.createDriver

class NoteViewModel: ViewModel() {


    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery


    val notesRepository = NotesRepository(createDriver())
    val notes: StateFlow<List<NoteEntity>> =
        notesRepository.getNotes()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )





    val filteredNotes: StateFlow<List<NoteEntity>> = combine(notes, _searchQuery) { noteList, query ->
        if (query.isBlank()) {
            noteList
        } else {
            noteList.filter { it.title.contains(query, ignoreCase = true) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )




    fun insertInNotes(title: String, content: String, reminderDate: String, priority: String){
        viewModelScope.launch {
            notesRepository.insert(
                title = title, content = content, reminderDate = reminderDate, priority = priority
            )
        }
    }


    fun updateNotes(title: String, content: String, priority: String, reminderDate: String, id: Long){
        viewModelScope.launch {
            notesRepository.updateNotes(
                title = title,
                content = content,
                priority = priority,
                reminderDate = reminderDate,
                id = id
            )
        }
    }


    fun getNoteById(id: Long)= notesRepository.getNoteById(id)


    fun deleteNote(id: Long){
        viewModelScope.launch {
            notesRepository.delete(id)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }






}