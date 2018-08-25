package com.android.acc.mynotes.dependencyinjection;

import android.app.Application;

import com.android.acc.mynotes.MyNotesApplication;
import com.android.acc.mynotes.ui.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(MyNotesApplication application);

    void inject(BaseActivity baseActivity);

    Application application();
}
