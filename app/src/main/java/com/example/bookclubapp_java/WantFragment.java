package com.example.bookclubapp_java;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class WantFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_readbook;
    ArrayList<String> bookId, bookTitle, bookAuthor, bookGenre, bookStatus, bookISBN;
    TextView readingTitle;

    DBHelper myDB;
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_want, container, false);

        recyclerView = view.findViewById(R.id.wantRecycle);
        add_readbook = view.findViewById(R.id.add_read);

        readingTitle = view.findViewById(R.id.TitleRead);
        readingTitle.setPaintFlags(readingTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
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
        bookStatus = new ArrayList<>();
        bookISBN = new ArrayList<>();

        storeWantData();
        customAdapter = new CustomAdapter(getActivity(),bookId,bookTitle,bookAuthor,bookGenre,bookStatus,bookISBN);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    void storeWantData() {
        Cursor cursor = myDB.readBookData();
        if (cursor != null && cursor.getCount() > 0) {
            int statusIndex = cursor.getColumnIndexOrThrow("book_status"); // Retrieve index of "status" column
            while (cursor.moveToNext()) {
                String status = cursor.getString(statusIndex); // Retrieve status using index
                if ("Want to read".equals(status)) { // Check if status is "Read"
                    bookId.add(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
                    bookTitle.add(cursor.getString(cursor.getColumnIndexOrThrow("book_title")));
                    bookAuthor.add(cursor.getString(cursor.getColumnIndexOrThrow("book_author")));
                    bookGenre.add(cursor.getString(cursor.getColumnIndexOrThrow("book_GENRE")));
                    bookISBN.add(cursor.getString(cursor.getColumnIndexOrThrow("book_isbn")));
                    bookStatus.add(status);
                }
            }
        } else {
            Toast.makeText(requireContext(), "No data found.", Toast.LENGTH_SHORT).show();
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}