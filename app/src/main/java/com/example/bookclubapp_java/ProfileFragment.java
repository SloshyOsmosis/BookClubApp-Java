package com.example.bookclubapp_java;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class ProfileFragment extends Fragment {

    TextView userName, profileText;
    FloatingActionButton changePFP;
    ImageView profilePicture;
    DBHelper dbHelper;
    Button logout, deleteUser;
    ActivityResultLauncher resultLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        dbHelper = new DBHelper(getActivity());

        profilePicture = view.findViewById(R.id.profilePicture);
        changePFP = view.findViewById(R.id.changePFP);
        userName = view.findViewById(R.id.userName_Text);

        logout = view.findViewById(R.id.logoutButton);
        deleteUser = view.findViewById(R.id.deleteAccountButton);

        profileText = view.findViewById(R.id.profileText);
        profileText.setPaintFlags(profileText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // Sets the username to the found username from the database
        String username = dbHelper.getUserName();
        if (username != null) {
            userName.setText(username);
        } else {
            userName.setText("Username Not found");
        }

        registerResult();
        changePFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
                resultLauncher.launch(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "User logged out.",Toast.LENGTH_SHORT).show();
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteDialog();
            }
        });

        return view;
    }

    private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try{
                            assert result.getData() != null;
                            Uri imageUri = result.getData().getData();
                            profilePicture.setImageURI(imageUri);
                        } catch (Exception e){
                            Toast.makeText(getContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    void confirmDeleteDialog(){
        String userName = new DBHelper(getContext()).getUserName();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete " + userName + "?");
        builder.setMessage("Are you sure you want to delete your account?" +  userName + "?" + " We will miss you...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(getContext());
                myDB.deleteUser(String.valueOf(userName));

                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "User has been deleted.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}