package com.example.bookclubapp_java;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    EditText messageEditText;
    LinearLayout messageContainer;
    Button sendMessage;
    TextView homeTitle;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        messageEditText = view.findViewById(R.id.messageEditText);
        messageContainer = view.findViewById(R.id.messageContainer);
        sendMessage = view.findViewById(R.id.sendMessageButton);

        dbHelper = new DBHelper(getActivity());

        homeTitle = view.findViewById(R.id.homeTitle);
        homeTitle.setPaintFlags(homeTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMessage();
            }
        });

        return view;
    }

    //This method will post a message to the chat room, inside i have done it so that it creates a new TextView where the message will be stored and displayed.
    public void postMessage(){
        String messageText = messageEditText.getText().toString().trim();
        if (!messageText.isEmpty()){
            //Fetches username from the database and displays it in the chatroom.
            String username = dbHelper.getUserName();
            String format = username + ": " + messageText;
            //Creates a new TextView that will be displayed in the linearLayout.
            TextView messageView = new TextView(getContext());
            messageView.setText(format);
            messageView.setTextSize(16);
            messageView.setTextColor(Color.BLACK);
            //Adds the message to the linearLayout.
            messageContainer.addView(messageView);
            messageEditText.getText().clear();
        }
    }
}