package com.android.acc.mynotes.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.android.acc.mynotes.data.NoteRepository;
import com.android.acc.mynotes.database.NoteEntry;

/* Adding a new note to the database.*/
public class AddNoteViewModel extends ViewModel {

    private NoteRepository repository;

    public AddNoteViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public void addNewNoteToDatabase(NoteEntry noteEntry) {
        new AddNewNoteAsyncTask().execute(noteEntry);
    }

    private class AddNewNoteAsyncTask extends AsyncTask<NoteEntry, Void, Void> {

        @Override
        protected Void doInBackground(NoteEntry... noteEntry) {
            repository.insertNote(noteEntry[0]);
            return null;
        }
    }
}
