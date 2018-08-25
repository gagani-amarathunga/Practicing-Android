package com.android.acc.mynotes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.android.acc.mynotes.data.NoteRepository;
import com.android.acc.mynotes.database.NoteEntry;

/* Updating an existing note in the database. */
public class NoteViewModel extends ViewModel {

    private NoteRepository repository;
    private LiveData<NoteEntry> note;

    public NoteViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public LiveData<NoteEntry> getNote(int note_id) {
        return repository.loadNoteById(note_id);
    }

    public void updateNoteInDatabase(NoteEntry noteEntry) {
        new UpdateNoteAsyncTask().execute(noteEntry);
    }

    private class UpdateNoteAsyncTask extends AsyncTask<NoteEntry, Void, Void> {

        @Override
        protected Void doInBackground(NoteEntry... noteEntry) {
            repository.updateNote(noteEntry[0]);
            return null;
        }
    }
}
