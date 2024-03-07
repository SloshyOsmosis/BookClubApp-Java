package com.example.bookclubapp_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResetActivity extends AppCompatActivity {

    TextView userName;
    EditText pass, repass;
    Button resetconfirm;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        userName = findViewById(R.id.userName_Reset_Text);
        pass = findViewById(R.id.password_Reset);
        repass = findViewById(R.id.repassword_Reset);
        resetconfirm = findViewById(R.id.resetConfirm);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        userName.setText(intent.getStringExtra("username"));

        resetconfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if(pass.equals(repass)) {


                    Boolean checkpasswordupdate = dbHelper.updatepassword(user, password);
                    if (checkpasswordupdate == true) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password Updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Password Update failed.", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(ResetActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}