package com.adnanozgurcelik.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.adnanozgurcelik.jetnote.util.DateConverter
import com.adnanozgurcelik.jetnote.util.UUIDConverter
import com.adnanozgurcelik.jetnote.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}