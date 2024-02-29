package com.adnanozgurcelik.jetnote.repository

import com.adnanozgurcelik.jetnote.data.NoteDatabaseDao
import com.adnanozgurcelik.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao
) {
    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note = note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.update(note = note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes()
        .flowOn(Dispatchers.IO)
        .conflate()
}