package com.olefa.notesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.olefa.notesapp.model.Converters
import com.olefa.notesapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}