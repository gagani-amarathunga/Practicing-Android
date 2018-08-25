package com.android.acc.mynotes.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.util.Log;
import android.view.View;

import com.android.acc.mynotes.MyNotesApplication;
import com.android.acc.mynotes.util.AppExecutors;
import com.android.acc.mynotes.viewmodel.MainViewModel;
import com.android.acc.mynotes.R;
import com.android.acc.mynotes.database.NoteEntry;
import com.android.acc.mynotes.ui.NoteAdapter;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends BaseActivity implements NoteAdapter.ItemClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    // Member variables for the adapter and RecyclerView
    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerViewNotes);

        ((MyNotesApplication) getApplication()).getApplicationComponent().inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new NoteAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        // Set the layout of the RecyclerView to a Staggered Grid Layout
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        new ItemTouchHelper(new SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
                return false;
            }

            // Called when the user swipes left or right on a ViewHolder to delete an item
            @Override
            public void onSwiped(final ViewHolder viewHolder, int direction) {
                AppExecutors.getsInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<NoteEntry> notes = mAdapter.getNotes();
                        viewModel.deleteNote(notes.get(position));
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);

        /*
         Set the Floating Action Button (FAB) to its corresponding View.
         An OnClickListener is attached to it, so that when it's clicked, a new intent will be created
         to launch the AddNoteActivity.
         */
        FloatingActionButton addNoteFab = findViewById(R.id.addNoteFab);

        addNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to start an AddNoteActivity
                Intent addNoteIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(addNoteIntent);
            }
        });

        setupViewModel();
    }

    /* Get notes via MainViewModel which caches data and it returns LiveData that executed in the background,
     * then the onChanged method of the observer which runs in the UI thread updates the UI.
     *
     * Uses LiveData and Lifecycle */
    private void setupViewModel() {
        /* getNotes() method returns LiveData that executed in background to handle DB tasks in background and
         * as it returns LiveData, observe method can be used. Observer takes a lifecycle owner as a parameter (this).
         *
         * onChanged of observe method runs in the UI thread to update the UI. */
        viewModel.getNotes().observe(this, new Observer<List<NoteEntry>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntry> noteEntries) {
                Log.d(TAG, "Updating list of notes from LiveData in ViewModel");
                mAdapter.setNotes(noteEntries);
            }
        });
    }

    /* Clicking on a list item to updata it.
     * OnItemClickListener passes its itemId to AddNoteActivity so that it can update the item.
     * */
    @Override
    public void onItemClickListener(int itemId) {
        // Launch AddNoteActivity adding the itemId as an extra in the intent
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        intent.putExtra(AddNoteActivity.EXTRA_NOTE_ID, itemId);
        startActivity(intent);
    }
}

