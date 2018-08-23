package com.android.acc.mynotes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.android.acc.mynotes.database.AppDatabase;
import com.android.acc.mynotes.database.NoteEntry;

/* Adding ViewModel to the DB operation of updating a note related with AddNoteActivity class.*/
public class AddNoteViewModel extends ViewModel {

    private LiveData<NoteEntry> note;

    // Constructor that calls loadNoteById of the noteDao to initialize the note variable
    public AddNoteViewModel(AppDatabase database, int noteId) {
        note = database.noteDao().loadNoteById(noteId);
    }

    // Getter for the note variable
    public LiveData<NoteEntry> getNote() {
        return note;
    }
}
