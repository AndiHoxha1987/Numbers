package com.example.numbers.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.numbers.R;
import com.example.numbers.adapter.ListFragmentAdapter;
import com.example.numbers.data.NumbersData;

public class ListFragment extends Fragment {

    private OnImageClickListener mCallBack;

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallBack= (OnImageClickListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+
                    "must implement OnImageClickListener");
        }
    }

    // Mandatory empty constructor
    public ListFragment() {
    }

    // Inflates the GridView of all resource images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        // Get a reference to the GridView in the fragment_list xml layout file
        GridView gridView = rootView.findViewById(R.id.images_grid_view);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        ListFragmentAdapter mAdapter = new ListFragmentAdapter(getContext(), NumbersData.getAll());

        // Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallBack.onImageSelected(position);
            }
        });

        return rootView;
    }
}
