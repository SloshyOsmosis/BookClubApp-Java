package com.example.bookclubapp_java;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList bookId, bookTitle, bookAuthor, bookGenre, bookISBN;
    Fragment fragment;

    CustomAdapter(Fragment fragment, Context context, ArrayList bookId,
                  ArrayList bookTitle, ArrayList bookAuthor,
                  ArrayList bookGenre, ArrayList bookISBN,
                  ArrayList bookStatus){
        this.fragment = fragment;
        this.context = context;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.bookISBN = bookISBN;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.bookIdText.setText(String.valueOf(bookId.get(position)));
        holder.bookTitleText.setText(String.valueOf(bookTitle.get(position)));
        holder.bookAuthorText.setText(String.valueOf(bookAuthor.get(position)));
        holder.bookGenreText.setText(String.valueOf(bookGenre.get(position)));
        holder.bookISBNText.setText(String.valueOf(bookISBN.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(bookId.get(position)));
                intent.putExtra("title", String.valueOf(bookTitle.get(position)));
                intent.putExtra("author", String.valueOf(bookAuthor.get(position)));
                intent.putExtra("genre", String.valueOf(bookGenre.get(position)));
                intent.putExtra("ISBN", String.valueOf(bookISBN.get(position)));
                //Refreshes the activity when updating books.
                fragment.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookIdText, bookTitleText, bookAuthorText, bookGenreText, bookISBNText;
        CardView mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdText = itemView.findViewById(R.id.bookIdText);
            bookTitleText = itemView.findViewById(R.id.bookTitleText);
            bookAuthorText = itemView.findViewById(R.id.bookAuthorText);
            bookGenreText = itemView.findViewById(R.id.bookGenreText);
            bookISBNText = itemView.findViewById(R.id.bookISBNText);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
