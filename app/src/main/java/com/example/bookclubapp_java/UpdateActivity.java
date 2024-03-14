package com.example.bookclubapp_java;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

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

        updateButton = findViewById(R.id.updateReadBook);

        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this, R.array.genre, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreInput.setAdapter(genreAdapter);
        genreInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusInput.setAdapter(statusAdapter);
        statusInput.setOnItemSelectedListener(this);

        getIntentData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genreState = genreInput.getSelectedItem().toString();
                String statusState = statusInput.getSelectedItem().toString();

                DBHelper myDB = new DBHelper(UpdateActivity.this);

                title = titleInput.getText().toString().trim();
                author = authorInput.getText().toString().trim();
                ISBN = ISBNInput.getText().toString().trim();
                myDB.updateLibraryData(id,title,author,genreState,ISBN,statusState);
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
            int statusPosition = statusAdapter.getPosition(status);
            statusInput.setSelection(statusPosition);

        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}