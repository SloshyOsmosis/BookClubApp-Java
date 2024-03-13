package com.example.bookclubapp_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList bookId, bookTitle, bookAuthor, bookGenre, bookISBN;

    CustomAdapter(Context context, ArrayList bookId,
                  ArrayList bookTitle, ArrayList bookAuthor,
                  ArrayList bookGenre, ArrayList bookISBN){
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
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.bookIdText.setText(String.valueOf(bookId.get(position)));
        holder.bookTitleText.setText(String.valueOf(bookTitle.get(position)));
        holder.bookAuthorText.setText(String.valueOf(bookAuthor.get(position)));
        holder.bookGenreText.setText(String.valueOf(bookGenre.get(position)));
        holder.bookISBNText.setText(String.valueOf(bookISBN.get(position)));

    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookIdText, bookTitleText, bookAuthorText, bookGenreText, bookISBNText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdText = itemView.findViewById(R.id.bookIdText);
            bookTitleText = itemView.findViewById(R.id.bookTitleText);
            bookAuthorText = itemView.findViewById(R.id.bookAuthorText);
            bookGenreText = itemView.findViewById(R.id.bookGenreText);
            bookISBNText = itemView.findViewById(R.id.bookISBNText);
        }
    }
}
