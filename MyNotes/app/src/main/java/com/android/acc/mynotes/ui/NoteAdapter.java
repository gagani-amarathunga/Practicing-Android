package com.android.acc.mynotes.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.acc.mynotes.R;
import com.android.acc.mynotes.database.NoteEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    // Constant for date format
    private static final String DATE_FORMAT = "d MMM, yyyy h:mm a";
    private Context mContext;

    // Member variable to handle item clicks
    final private ItemClickListener mItemClickListener;

    private List<NoteEntry> mNoteEntries;

    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public NoteAdapter(Context context, ItemClickListener listener) {
        this.mContext = context;
        this.mItemClickListener = listener;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new NoteViewHolder that holds the view for each note
     */
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the note layout to a view
        View view = LayoutInflater.from(mContext).inflate(R.layout.note_layout, parent, false);
        return new NoteViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // Determine the values of the necessary data
        NoteEntry noteEntry = mNoteEntries.get(position);
        String noteContent = noteEntry.getContent();
        String updatedAt = dateFormat.format(noteEntry.getUpdatedAt());

        //Set values
        holder.noteContentView.setText(noteContent);
        holder.updatedAtView.setText(updatedAt);
    }

    @Override
    public int getItemCount() {
        if (mNoteEntries == null) {
            return 0;
        }
        return mNoteEntries.size();
    }

    public List<NoteEntry> getNotes() {
        return mNoteEntries;
    }

    /**
     * When data changes, this method updates the list of Note Entries
     * and notifies the adapter to use the new values on it
     */
    public void setNotes(List<NoteEntry> noteEntries) {
        mNoteEntries = noteEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    /* Inner class for creating ViewHolders */
    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView noteContentView;
        TextView updatedAtView;

        public NoteViewHolder(View itemView) {
            super(itemView);

            noteContentView = itemView.findViewById(R.id.noteContent);
            updatedAtView = itemView.findViewById(R.id.noteUpdatedAt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mNoteEntries.get(getAdapterPosition()).getNote_id();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}
