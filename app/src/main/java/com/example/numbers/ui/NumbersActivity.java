package com.example.numbers.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.numbers.R;
import com.example.numbers.data.NumbersData;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            ImageFragment firstFragment = new ImageFragment();
            firstFragment.setImageIds(NumbersData.getNumbers());
            int firstIndex = getIntent().getIntExtra("firstIndex", 0);
            firstFragment.setListIndex(firstIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.first_container, firstFragment)
                    .commit();

            ImageFragment actionFragment = new ImageFragment();
            actionFragment.setImageIds(NumbersData.getActions());
            int actionIndex = getIntent().getIntExtra("actionIndex", 0);
            actionFragment.setListIndex(actionIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.calc_container, actionFragment)
                    .commit();

            ImageFragment secondFragment = new ImageFragment();
            secondFragment.setImageIds(NumbersData.getNumbers());
            int secondIndex = getIntent().getIntExtra("secondIndex", 0);
            secondFragment.setListIndex(secondIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.second_container, secondFragment)
                    .commit();

        }
    }
}
