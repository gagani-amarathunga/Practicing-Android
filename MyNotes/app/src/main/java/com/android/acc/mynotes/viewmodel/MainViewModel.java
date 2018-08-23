package com.android.acc.mynotes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.acc.mynotes.database.AppDatabase;
import com.android.acc.mynotes.database.NoteEntry;

import java.util.List;

/* Adding ViewModel to the DB operation of getting all the notes related with MainActivity.
 *
 * This class extends by AndroidViewModel to implement ViewModel to cache data.
 * It prevents memory leaks occurs at rotations due to recreation of the Activity. */
public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    // List of NoteEntry objects wrapped in a LiveData object to be cached using ViewModel
    private LiveData<List<NoteEntry>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);

        // Instance of the database
        AppDatabase database = AppDatabase.getsInstance(this.getApplication());
        Log.d(TAG, "Retrieving notes from the database.");
        // Initialize the member variable
        notes = database.noteDao().loadAllNotes();
    }

    /* Returns all the notes in the database */
    public LiveData<List<NoteEntry>> getNotes() {
        return notes;
    }

}
