package com.android.acc.mynotes.dependencyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.android.acc.mynotes.data.NoteRepository;
import com.android.acc.mynotes.database.AppDatabase;
import com.android.acc.mynotes.database.NoteDao;
import com.android.acc.mynotes.viewmodel.AddNoteViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final AppDatabase database;
    private static final String DATABASE_NAME = "notelist";

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(application,
                AppDatabase.class,
                DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    NoteRepository provideNoteRepositry(NoteDao noteDao) {
        return new NoteRepository(noteDao);
    }

    @Provides
    @Singleton
    NoteDao provideNoteDao(AppDatabase database) {
        return database.noteDao();
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Application application) {
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(NoteRepository repository) {
        return new AddNoteViewModelFactory(repository);
    }
}
