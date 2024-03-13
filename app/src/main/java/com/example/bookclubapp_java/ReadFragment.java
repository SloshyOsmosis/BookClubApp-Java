package com.example.bookclubapp_java;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ReadFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_readbook;
    ArrayList<String> bookId, bookTitle, bookAuthor, bookGenre, bookISBN;

    DBHelper myDB;
    CustomAdapter customAdapter;

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
        myDB = new DBHelper(requireContext());
        bookId = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookGenre = new ArrayList<>();
        bookISBN = new ArrayList<>();

        storeData();
        customAdapter = new CustomAdapter(requireContext(),bookId,bookTitle,bookAuthor,bookGenre,bookISBN);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    void storeData(){
        Cursor cursor = myDB.readBookData();
        if(cursor.getCount() == 0){
            Toast.makeText(requireContext(),"No data found.", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()){
                bookId.add(cursor.getString(0));
                bookTitle.add(cursor.getString(1));
                bookAuthor.add(cursor.getString(2));
                bookGenre.add(cursor.getString(3));
                bookISBN.add(cursor.getString(4));
            }
        }
    }
}