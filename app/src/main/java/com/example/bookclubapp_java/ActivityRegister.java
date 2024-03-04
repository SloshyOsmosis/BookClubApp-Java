package com.example.bookclubapp_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser, etPass, etRepass, etEmail;
    Button btnRegister, btnGoToLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.userName);
        etPass = findViewById(R.id.password);
        etRepass = findViewById(R.id.retypePassword);
        btnRegister = findViewById(R.id.regButton);
        dbHelper = new DBHelper(this);
        btnGoToLogin = findViewById(R.id.goToLogin);
        btnGoToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityRegister.this, MainActivity.class);
            startActivity(intent);
        });
        btnRegister.setOnClickListener(view -> {
            String user, pass, repass, email;
            user = etUser.getText().toString();
            pass = etPass.getText().toString();
            repass = etRepass.getText().toString();
            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty() ){
                Toast.makeText(ActivityRegister.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();
            } else{
                if(pass.equals(repass)) {
                    if (dbHelper.checkUserName(user)){
                        Toast.makeText(ActivityRegister.this, "User already exists", Toast.LENGTH_LONG).show();
                        return;
                    }
                    //Continues with registration if both password and retype password fields are matching.
                    boolean registeredSuccess = dbHelper.insertData(user,pass);
                    if (registeredSuccess)
                        Toast.makeText(ActivityRegister.this, "User registered Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(ActivityRegister.this, "User registration Failed. Please try again.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ActivityRegister.this, "Passwords do not match.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
