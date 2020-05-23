package com.example.numbers.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import com.example.numbers.R;
import com.example.numbers.data.NumbersData;

public class MainActivity extends AppCompatActivity implements ListFragment.OnImageClickListener{

    // Variables to store the values for the list index of the selected images
    // The default value will be index = 0
    private int firstIndex;
    private int actionIndex;
    private int secondIndex;

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Determine if you're creating a two-pane or single-pane display
        if(findViewById(R.id.numbers_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Change the GridView to space out the images more on tablet
            GridView gridView = findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            // Getting rid of the "Next" button that appears on phones for launching a separate activity
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            if(savedInstanceState == null) {
                // In two-pane mode, add initial containerFragments to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new first fragment
                ImageFragment firstNumberFragment = new ImageFragment();
                firstNumberFragment.setImageIds(NumbersData.getNumbers());
                // Add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.first_number_container, firstNumberFragment)
                        .commit();

                // New action fragment
                ImageFragment actionsFragment = new ImageFragment();
                actionsFragment.setImageIds(NumbersData.getActions());
                fragmentManager.beginTransaction()
                        .add(R.id.actions_container, actionsFragment)
                        .commit();

                // New Second fragment
                ImageFragment secondNumberFragment = new ImageFragment();
                secondNumberFragment.setImageIds(NumbersData.getNumbers());
                fragmentManager.beginTransaction()
                        .add(R.id.second_number_container, secondNumberFragment)
                        .commit();
            }
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }

    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
        // Create a Toast that displays the position that was clicked
        int index = position+1;
        Toast.makeText(this, "Position clicked = " + index, Toast.LENGTH_SHORT).show();

        // Handle the two-pane case and replace existing fragments right when a new image is selected from the listFragment
        if (mTwoPane) {
            // Create two=pane interaction

            ImageFragment newFragment = new ImageFragment();

            if(position<6){
                newFragment.setImageIds(NumbersData.getNumbers());
                newFragment.setListIndex(position);
                // Replace the old head fragment with a new one
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.first_number_container, newFragment)
                        .commit();
            }else if(position<8){
                newFragment.setImageIds(NumbersData.getActions());
                newFragment.setListIndex(position-6);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.actions_container, newFragment)
                        .commit();
            }else if(position<14){
                newFragment.setImageIds(NumbersData.getNumbers());
                newFragment.setListIndex(position-8);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.second_number_container, newFragment)
                        .commit();
            }
        } else {

            // Handle the single-pane phone case by passing information in a Bundle attached to an Intent
            if(position<6){
                firstIndex = position;
            }else if(position<8){
                actionIndex = position-6;
            }else if(position<14){
                secondIndex = position-8;
            }

            // Put this information in a Bundle and attach it to an Intent that will launch NumbersActivity
            Bundle b = new Bundle();
            b.putInt("firstIndex", firstIndex);
            b.putInt("actionIndex", actionIndex);
            b.putInt("secondIndex", secondIndex);

            // Attach the Bundle to an intent
            final Intent intent = new Intent(this, NumbersActivity.class);
            intent.putExtras(b);

            // The "Next" button launches a new NumbersActivity
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }

    }
}
