package com.example.bookclubapp_java;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText titleInput, authorInput, ISBNInput;
    Spinner genreInput, statusInput;
    Button updateButton, deleteButton;
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
        deleteButton = findViewById(R.id.deleteBook);

        //Changes the spinner value
        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this, R.array.genre, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreInput.setAdapter(genreAdapter);
        genreInput.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusInput.setAdapter(statusAdapter);
        statusInput.setOnItemSelectedListener(this);

        getIntentData();
        //Update book with new values
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String genreState = genreInput.getSelectedItem().toString();
                String statusState = statusInput.getSelectedItem().toString();

                DBHelper myDB = new DBHelper(UpdateActivity.this);

                title = titleInput.getText().toString().trim();
                author = authorInput.getText().toString().trim();
                ISBN = ISBNInput.getText().toString().trim();
                //Updates the current library book
                myDB.updateLibraryData(id,title,author,genreState,ISBN,statusState);

                //Refreshes the fragment once a book has been added
                ReadFragment readFragment = (ReadFragment) getSupportFragmentManager().findFragmentByTag("ReadFragment");
                if (readFragment != null){
                    readFragment.refresh();
                }
                ReadFragment readingFragment = (ReadFragment) getSupportFragmentManager().findFragmentByTag("ReadingFragment");
                if (readFragment != null){
                    readFragment.refresh();
                }
                ReadFragment wantFragment = (ReadFragment) getSupportFragmentManager().findFragmentByTag("WantFragment");
                if (readFragment != null){
                    readFragment.refresh();
                }
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });

    }

    // Retrieves the chosen book data to display
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

    void confirmDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete " +  title + "?" + " This books status is currently " + status + "...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(UpdateActivity.this);
                myDB.deleteLibraryData(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}