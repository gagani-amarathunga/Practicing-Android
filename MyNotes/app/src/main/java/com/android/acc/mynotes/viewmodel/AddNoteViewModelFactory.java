package com.android.acc.mynotes.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.android.acc.mynotes.data.NoteRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AddNoteViewModelFactory implements ViewModelProvider.Factory {

    private final NoteRepository repository;

    @Inject
    public AddNoteViewModelFactory(NoteRepository repository) {
        this.repository = repository;
    }

    /* Returns instances of ViewModel classes. */
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(AddNoteViewModel.class)) {
            return (T) new AddNoteViewModel(repository);
        } else if (modelClass.isAssignableFrom(NoteViewModel.class)) {
            return (T) new NoteViewModel(repository);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        } else {
            throw new IllegalArgumentException("ViewModel not found");
        }
    }
}
