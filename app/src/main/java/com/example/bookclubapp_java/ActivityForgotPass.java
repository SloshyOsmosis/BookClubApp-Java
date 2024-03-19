package com.example.bookclubapp_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityForgotPass extends AppCompatActivity {

    EditText userName;
    Button reset;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        userName = (EditText) findViewById(R.id.userName);
        reset = (Button) findViewById(R.id.resetButton);

        dbHelper = new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();

                //Calls on the checkUserName method in DBHelper to check if the user exists in the database
                boolean checkUser = dbHelper.checkUserName(user);
                if(checkUser==true)
                {
                    Intent intent = new Intent(ActivityForgotPass.this,ResetActivity.class);
                    //Displays the username in the empty TextView
                    intent.putExtra("username", user);
                    startActivity(intent);
                }else
                {
                    //Toast is displayed when there is no matching username found.
                    Toast.makeText(ActivityForgotPass.this,"User does not exist.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
