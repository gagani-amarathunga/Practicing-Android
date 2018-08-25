package com.android.acc.mynotes.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/* Provide an API to reading and writing data.
 * ie. This class has all the operations related to the database.
 *
 * LiveData which runs in a background thread is used to get notified when there's a change in the db.
 * insert, update, delete do not need LiveData.
 *
 * Dao (Data Access Object */
@Dao
public interface NoteDao {

    // Update the UI by retrieving all the notes in the database
    @Query("SELECT * FROM note ORDER BY updated_at DESC")
    LiveData<List<NoteEntry>> loadAllNotes();

    // Insert a note
    @Insert
    void insertNote(NoteEntry noteEntry);

    // Update a note
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(NoteEntry noteEntry);

    // Delete a note
    @Delete
    void deleteNote(NoteEntry noteEntry);

    /* Getting a note by ID to update */
    @Query("SELECT * FROM note WHERE note_id = :id")
    LiveData<NoteEntry> loadNoteById(int id);
}
