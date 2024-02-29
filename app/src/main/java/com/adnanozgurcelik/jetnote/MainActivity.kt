package com.adnanozgurcelik.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adnanozgurcelik.jetnote.screen.NoteScreen
import com.adnanozgurcelik.jetnote.viewModel.NoteViewModel
import com.adnanozgurcelik.jetnote.ui.theme.JetNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {

                    //val noteViewModel = viewModel<NoteViewModel>() // also works
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel) {
    val notesList = noteViewModel.noteList.collectAsState().value

    NoteScreen(note = notesList,
        onRemoveNote = {
            noteViewModel.removeNote(it)
        },
        onAddNote = {
            noteViewModel.addNote(it)
        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteTheme {
        //
    }
}