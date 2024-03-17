package com.example.bookclubapp_java;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class BookFragment extends Fragment {

    TextView shelf;

    Button expRead, expReading, expWant;

    ReadFragment readFragment = new ReadFragment();
    ReadingFragment readingFragment = new ReadingFragment();
    WantFragment wantFragment = new WantFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book, container, false);

        expRead = view.findViewById(R.id.expandRead);
        expReading = view.findViewById(R.id.expandReading);
        expWant = view.findViewById(R.id.expandWant);

        //Underlines the buttons
        expRead.setPaintFlags(expRead.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        expReading.setPaintFlags(expReading.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        expWant.setPaintFlags(expWant.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //Underline the title
        shelf = view.findViewById(R.id.shelves);
        shelf.setPaintFlags(shelf.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //Replaces the current fragment based on which list the user clicks on
        expRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.container,readFragment).commit();
            }
        });
        expReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.container,readingFragment).commit();
            }
        });
        expWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.container,wantFragment).commit();
            }
        });



        return view;
    }
}