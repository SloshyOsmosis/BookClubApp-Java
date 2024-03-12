package com.example.bookclubapp_java;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddReadBook extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText titleInput, authorInput, ISBNInput;
    Button addReadButton;
    Spinner spinnerGenre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_read_book);

        titleInput = findViewById(R.id.input_Title);
        authorInput = findViewById(R.id.input_Author);
        ISBNInput = findViewById(R.id.input_ISBN);

        addReadButton = findViewById(R.id.addReadBook);

        spinnerGenre=findViewById(R.id.genreSpinner);

        //Created a Spinner for selecting genres.
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.genre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
        spinnerGenre.setOnItemSelectedListener(this);

        addReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Grabs the selected genre from the item spinner.
                String genreState = spinnerGenre.getSelectedItem().toString();
                DBHelper myDB = new DBHelper(AddReadBook.this);
                myDB.addBook(titleInput.getText().toString().trim(),
                        authorInput.getText().toString().trim(),
                        genreState,
                        ISBNInput.getText().toString().trim());
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedGenre = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}