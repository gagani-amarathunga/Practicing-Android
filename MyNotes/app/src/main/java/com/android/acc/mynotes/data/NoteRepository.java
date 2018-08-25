package com.android.acc.mynotes.data;

import android.arch.lifecycle.LiveData;

import com.android.acc.mynotes.database.NoteDao;
import com.android.acc.mynotes.database.NoteEntry;

import java.util.List;

/* Handles data operations.*/
public class NoteRepository {

    private final NoteDao noteDao;

    public NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public LiveData<List<NoteEntry>> loadAllNotes() {
        return noteDao.loadAllNotes();
    }

    public LiveData<NoteEntry> loadNoteById(int note_id) {
        return noteDao.loadNoteById(note_id);
    }

    public void insertNote(NoteEntry noteEntry) {
        noteDao.insertNote(noteEntry);
    }

    public void updateNote(NoteEntry noteEntry) {
        noteDao.updateNote(noteEntry);
    }

    public void deleteNote(NoteEntry noteEntry) {
        noteDao.deleteNote(noteEntry);
    }
}
