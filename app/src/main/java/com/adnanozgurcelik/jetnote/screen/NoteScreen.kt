package com.adnanozgurcelik.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.adnanozgurcelik.jetnote.R
import com.adnanozgurcelik.jetnote.components.NoteButtton
import com.adnanozgurcelik.jetnote.components.NoteInputText
import com.adnanozgurcelik.jetnote.model.Note
import com.adnanozgurcelik.jetnote.util.formatDate
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(note: List<Note>,
               onRemoveNote: (Note) -> Unit,
               onAddNote: (Note) -> Unit) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name),
                              color = Color.White)
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon",
                    tint = Color.White)
            },
            colors = TopAppBarDefaults.topAppBarColors(Color(0xFF175383)))

        //Content
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(modifier = Modifier
                .padding(top = 9.dp,
                    bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all {
                        char ->
                        char.isLetter() || char.isWhitespace()
                        }) title = it
                })

            NoteInputText(modifier = Modifier
                .padding(top = 9.dp,
                    bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all {
                        char ->
                        char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            NoteButtton(text = "Save",
                onClick = {
                    //save - add note - delete note
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(
                            Note(title = title,
                            description = description)
                        )
                        title = ""
                        description = ""
                        Toast.makeText(context,"Note has been loaded", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        Divider(modifier = Modifier
            .padding(10.dp))

        LazyColumn {
            items(note) {
                note ->
                NoteRow(note = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                        Toast.makeText(context, "Note has been removed", Toast.LENGTH_SHORT).show()
                    })
            }
        }
    }
}

@Composable
fun NoteRow(modifier: Modifier = Modifier,
            note: Note,
            onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFF777171),
        shadowElevation = 6.dp) {
        Column(modifier
            .clickable {
                onNoteClicked(note)
            }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title,
                style = MaterialTheme.typography.bodyMedium)

            Text(text = note.description,
                style = MaterialTheme.typography.bodyMedium)

            Text(text = formatDate(note.date.time),
                style = MaterialTheme.typography.bodySmall)
        }
    }
}


