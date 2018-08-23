package com.android.acc.mynotes.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/* Class to convert the Date data type to a timestamp as SQLite database cannot save Date datatype. */
public class DateConverter {

    // Convert timestamp to Date. Room uses this method when retrieving date from the DB
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    // Convert Date to timestamp. Room uses this method when saving the date to the db.
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
