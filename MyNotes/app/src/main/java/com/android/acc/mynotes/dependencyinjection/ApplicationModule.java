package com.android.acc.mynotes.dependencyinjection;

import android.app.Application;

import com.android.acc.mynotes.MyNotesApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final MyNotesApplication application;

    public ApplicationModule(MyNotesApplication application) {
        this.application = application;
    }

    /* Provides application context */
    @Provides
    MyNotesApplication provideMyNoteApplication() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
