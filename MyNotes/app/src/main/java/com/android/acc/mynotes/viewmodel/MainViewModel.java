package com.android.acc.mynotes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.android.acc.mynotes.data.NoteRepository;
import com.android.acc.mynotes.database.NoteEntry;

import java.util.List;

/* Display all notes of the database and deleting a note in the database. */
public class MainViewModel extends ViewModel {

    private NoteRepository repository;

    public MainViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<NoteEntry>> getNotes() {
        return repository.loadAllNotes();
    }

    public void deleteNote(NoteEntry noteEntry) {
        DeleteNoteAsyncTask deleteNote = new DeleteNoteAsyncTask();
        deleteNote.execute(noteEntry);
    }

    private class DeleteNoteAsyncTask extends AsyncTask<NoteEntry, Void, Void> {

        @Override
        protected Void doInBackground(NoteEntry... noteEntry) {
            repository.deleteNote(noteEntry[0]);
            return null;
        }
    }
}
