package com.olefa.notesapp.screen.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olefa.notesapp.R
import com.olefa.notesapp.components.NoteButton
import com.olefa.notesapp.components.NoteInputText
import com.olefa.notesapp.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val noteViewModel = viewModel<NoteViewModel>()
//    val noteViewModel : NoteViewModel by viewModels()
    val notes by noteViewModel.noteList.collectAsState()

    NoteScreen(notes = notes,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon")
            },
//            backgroundColor = Color(0xFFDADFE3)
        )

        // Content
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                } )

            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            NoteButton(text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title,
                            description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added",
                            Toast.LENGTH_SHORT).show()
                    }
                })

        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
//            items(notes){ note ->
//                NoteRow(note = note,
//                    onNoteClicked = { onRemoveNote(it) })
//            }
            items(notes.size){ index ->
                NoteRow(note = notes[index],
                    onNoteClicked = { onRemoveNote(it) })
            }
        }

    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit) {
    val formatter = SimpleDateFormat("EEE, d MM yyyy HH:mm:ss", Locale.getDefault())
    val dateString = formatter.format(note.entryDate)
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ){
        Column(modifier
            .clickable { onNoteClicked(note) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = dateString,
                style = MaterialTheme.typography.bodyMedium
            )


        }


    }

}



//@ExperimentalComposeUiApi
//@Preview(showBackground = true)
//@Composable
//fun NotesScreenPreview() {
//    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
//}