package com.olefa.notesapp.repository

import com.olefa.notesapp.data.NoteDatabaseDao
import com.olefa.notesapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao
){

    suspend fun addNote(note: Note) = noteDatabaseDao.addNote(note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteNoteById(id: UUID) = noteDatabaseDao.deleteNoteById(id)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAllNotes()
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes()
        .flowOn(Dispatchers.IO)
        .conflate()

}