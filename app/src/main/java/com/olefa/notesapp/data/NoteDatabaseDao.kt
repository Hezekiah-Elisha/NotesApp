package com.olefa.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.olefa.notesapp.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * FROM notes")
    fun getNotes():
            Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: UUID): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: UUID)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: Note)

}
