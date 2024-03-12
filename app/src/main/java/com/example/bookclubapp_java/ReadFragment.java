package com.example.bookclubapp_java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ReadFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_readbook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        recyclerView = view.findViewById(R.id.readRecycle);
        add_readbook = view.findViewById(R.id.add_read);
        add_readbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = requireContext();
                Intent intent = new Intent(context, AddReadBook.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}