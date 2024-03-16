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
    private ArrayList<Book> books;
    private Activity activity;

    CustomAdapter(Activity activity, ArrayList<Book> books){
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.books = books;
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
        Book addedBook = books.get(position);
        holder.bookTitleText.setText(addedBook.getTitle());
        holder.bookAuthorText.setText(addedBook.getAuthor());
        holder.bookGenreText.setText(addedBook.getGenre());
        holder.bookISBNText.setText(addedBook.getIsbn());
        holder.bookStatusText.setText(addedBook.getStatus());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(addedBook.getId()));
                intent.putExtra("title", addedBook.getTitle());
                intent.putExtra("author", addedBook.getAuthor());
                intent.putExtra("genre", addedBook.getGenre());
                intent.putExtra("ISBN", addedBook.getIsbn());
                intent.putExtra("status", addedBook.getStatus());
                //Refreshes the activity when updating books.
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookIdText, bookTitleText, bookAuthorText, bookGenreText, bookISBNText, bookStatusText;
        CardView mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdText = itemView.findViewById(R.id.bookIdText);
            bookTitleText = itemView.findViewById(R.id.bookTitleText);
            bookAuthorText = itemView.findViewById(R.id.bookAuthorText);
            bookGenreText = itemView.findViewById(R.id.bookGenreText);
            bookISBNText = itemView.findViewById(R.id.bookISBNText);
            bookStatusText = itemView.findViewById(R.id.bookStatusText);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
