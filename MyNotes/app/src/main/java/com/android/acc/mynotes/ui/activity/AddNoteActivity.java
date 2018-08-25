package com.android.acc.mynotes.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.acc.mynotes.MyNotesApplication;
import com.android.acc.mynotes.viewmodel.AddNoteViewModel;
import com.android.acc.mynotes.util.AppExecutors;
import com.android.acc.mynotes.R;
import com.android.acc.mynotes.database.AppDatabase;
import com.android.acc.mynotes.database.NoteEntry;
import com.android.acc.mynotes.viewmodel.NoteViewModel;

import java.util.Date;

public class AddNoteActivity extends BaseActivity {

    private EditText mNoteContentEditText;
    private Button mButton;

    // Extra for the note ID to be received in the intent
    public static final String EXTRA_NOTE_ID = "extraNoteId";

    // Extra for the note ID to be received after rotation
    public static final String INSTANCE_NOTE_ID = "instanceNoteId";

    // Constant for the default note ID to be used when item is not to be updated
    private static final int DEFAULT_NOTE_ID = -1;
    private int mNoteId = DEFAULT_NOTE_ID;

    // Member variable for the database
    private AppDatabase mDb;
    private Context context;

    private AddNoteViewModel mAddNoteViewModel; // Adding a note
    private NoteViewModel mNoteViewModel; // Updating a note

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ((MyNotesApplication) getApplication()).getApplicationComponent().inject(this);

        context = this;
        mAddNoteViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddNoteViewModel.class);
        mNoteViewModel = ViewModelProviders.of(this, viewModelFactory).get(NoteViewModel.class);

        initView();

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_NOTE_ID)) {
            mNoteId = savedInstanceState.getInt(INSTANCE_NOTE_ID, DEFAULT_NOTE_ID);
        }

        Intent intent = getIntent();

        // Receive the Item ID of the clicked list item of the list via intent.
        if (intent != null && intent.hasExtra(EXTRA_NOTE_ID)) {
            mButton.setText(R.string.update_button);

            if (mNoteId == DEFAULT_NOTE_ID) {// Populate the UI
                // Getting the Item ID of the clicked list item
                mNoteId = intent.getIntExtra(EXTRA_NOTE_ID, DEFAULT_NOTE_ID);

                mNoteViewModel.getNote(mNoteId).observe(this, new Observer<NoteEntry>() {
                    @Override
                    public void onChanged(@Nullable NoteEntry noteEntry) {
                        if (noteEntry != null) {
                            populateUI(noteEntry);
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_NOTE_ID, mNoteId);
        super.onSaveInstanceState(outState);
    }

    /**
     * Initializing the member variables
     */
    private void initView() {
        mNoteContentEditText = findViewById(R.id.noteContentEditText);
        mButton = findViewById(R.id.addNoteButton);

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addNoteButtonClicked();
            }
        });
    }

    /**
     * Populating the UI in update mode
     *
     * @param note the NoteEntry to populate the UI
     */
    private void populateUI(NoteEntry note) {
        if (note == null) {
            return;
        }

        // Setting new values by calling the methods of NoteEntry obj
        mNoteContentEditText.setText(note.getContent());
    }

    /**
     * This method is called when the "Add" button is clicked.
     * It retrieves user input and inserts that new note content into the underlying database.
     * <p>
     * When the Add button is clicked, it saves the content to the DB, updates LiveData, Calls the onChange method of Observer
     * in the MainActivity to notify the observer and then updates the UI.
     */
    public void addNoteButtonClicked() {
        String noteContent = mNoteContentEditText.getText().toString();
        Date date = new Date();

        final NoteEntry note = new NoteEntry(noteContent, date);

        AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mNoteId == DEFAULT_NOTE_ID) { // Insert new note
                    mAddNoteViewModel.addNewNoteToDatabase(note);
                } else { // Update note
                    note.setNote_id(mNoteId);
                    mNoteViewModel.updateNoteInDatabase(note);
                }
            }
        });

        // Using runOnUiThread give toast messages to the user to indicate the operations.
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mNoteId == DEFAULT_NOTE_ID) {
                    Toast.makeText(context, "Note added.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Note updated.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
