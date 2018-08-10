package com.android.horizontallistview.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.horizontallistview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Passing an ID to identify the fragment for vegetable items
        Bundle bundle = new Bundle();
        bundle.putInt("ID", 1);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentFruits = fragmentManager.findFragmentById(R.id.fragmentFruits);

        // First fragment for fruit items
        if (fragmentFruits == null) {
            fragmentFruits = new ItemsFragment();
            fragmentManager.beginTransaction().add(R.id.fragmentFruits, fragmentFruits).commit();
        }

        // Adding second fragment dynamically for vegetable items
        Fragment fragmentVegetables = new ItemsFragment();
        fragmentVegetables.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentVegetables, fragmentVegetables);
        transaction.commit();
    }
}
