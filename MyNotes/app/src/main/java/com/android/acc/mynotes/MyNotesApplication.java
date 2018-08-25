package com.android.acc.mynotes;

import android.app.Application;

import com.android.acc.mynotes.dependencyinjection.ApplicationComponent;
import com.android.acc.mynotes.dependencyinjection.ApplicationModule;
import com.android.acc.mynotes.dependencyinjection.DaggerApplicationComponent;
import com.android.acc.mynotes.dependencyinjection.RoomModule;

/* Entry point to the application. */
public class MyNotesApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /* Initialization of Dagger through a builder to given modules */
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();

        // Injecting into MyNotesApplication
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
