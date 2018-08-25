package com.android.acc.mynotes.ui.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.acc.mynotes.MyNotesApplication;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyNotesApplication) getApplication()).getApplicationComponent().inject(this);
    }
}
