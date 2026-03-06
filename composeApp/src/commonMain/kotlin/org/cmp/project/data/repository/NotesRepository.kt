package org.cmp.project.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.cmp.project.data.sqldelight.NoteEntity
import org.cmp.project.data.sqldelight.NotesDatabase

class NotesRepository(driver: SqlDriver) {
    private val database = NotesDatabase(driver)
    private val queries = database.noteQueries


    fun getNotes(): Flow<List<NoteEntity>> {
        return queries.getAllNotes()
            .asFlow()
            .mapToList(Dispatchers.IO)

    }


    suspend fun updateNotes(
        title: String,
        content: String,
        priority: String,
        reminderDate: String,
        id: Long
    ) =
        withContext(Dispatchers.IO) {
            queries.updateNote(
                title = title,
                content = content,
                priority = priority,
                reminderDate = reminderDate,
                id  = id
            )
        }


    fun insert(title: String, content: String, reminderDate: String, priority: String) {
        queries.insertNote(
            title = title,
            content = content,
            priority = priority,
            reminderDate = reminderDate
        )
    }

    fun delete(id: Long) {
        queries.deleteNote(id)
    }



    fun getNoteById(id: Long): Flow<NoteEntity?> {
        return queries.getNoteById(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
    }


}