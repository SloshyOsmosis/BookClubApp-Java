package com.example.bookclubapp_java;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    EditText titleInput, authorInput, ISBNInput;
    Spinner genreInput, statusInput;
    Button updateButton;
    String id,title, author, genre, ISBN, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleInput = findViewById(R.id.input_Title2);
        authorInput = findViewById(R.id.input_Author2);
        ISBNInput = findViewById(R.id.input_ISBN2);

        genreInput = findViewById(R.id.genreSpinner2);
        statusInput = findViewById(R.id.statusSpinner2);
        getIntentData();
        updateButton = findViewById(R.id.updateReadBook);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper myDB = new DBHelper(UpdateActivity.this);
                myDB.updateLibraryData(id,title,author,genre,ISBN,status);
            }
        });

    }

    void getIntentData(){
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("ISBN") &&
                getIntent().hasExtra("genre") &&
                getIntent().hasExtra("status")){
            //Getting values
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            genre = getIntent().getStringExtra("genre");
            ISBN = getIntent().getStringExtra("ISBN");
            status = getIntent().getStringExtra("status");

            //Setting EditText values
            titleInput.setText(title);
            authorInput.setText(author);
            ISBNInput.setText(ISBN);

            ArrayAdapter<CharSequence> genreAdapter = (ArrayAdapter<CharSequence>) genreInput.getAdapter();
            int genrePosition = genreAdapter.getPosition(genre);
            genreInput.setSelection(genrePosition);

            ArrayAdapter<CharSequence> statusAdapter = (ArrayAdapter<CharSequence>) statusInput.getAdapter();
            int statusPosition = statusAdapter.getPosition(genre);
            statusInput.setSelection(statusPosition);

        }else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}