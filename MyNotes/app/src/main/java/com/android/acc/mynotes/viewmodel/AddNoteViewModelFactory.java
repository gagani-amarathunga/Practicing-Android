package com.android.acc.mynotes.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.android.acc.mynotes.database.AppDatabase;

/* ViewModelFactory class to pass the the ID of the note to ViewModel when updating a note */
public class AddNoteViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mNoteId; // ID of the note that needs to be updated

    public AddNoteViewModelFactory(AppDatabase database, int noteId) {
        mDb = database;
        mNoteId = noteId;
    }

    // @return an instance of AddNoteViewModel class that uses the parameters mDb and mNoteId in its constructor.
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddNoteViewModel(mDb, mNoteId);
    }

}
