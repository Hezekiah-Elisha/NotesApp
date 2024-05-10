package com.olefa.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "entry_date")
    val entryDate: Date = Date.from(Instant.now())
//    q: How do i save the date to now in the right format?
//    a: You can use the Date.from(Instant.now()) function to get the current date and time in the right format.

)