package com.android.acc.mynotes.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/* Define database tables */
@Entity(tableName = "note")
public class NoteEntry {

    @PrimaryKey(autoGenerate = true)
    private int note_id;

    @ColumnInfo(name = "note_content")
    private String content;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    public NoteEntry(String content, Date updatedAt) {
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
