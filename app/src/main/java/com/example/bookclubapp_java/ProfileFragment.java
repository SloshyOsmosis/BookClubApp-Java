package com.example.bookclubapp_java;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.getIntent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileFragment extends Fragment {

    TextView userName;
    Button changePFP;
    ImageView profilePicture;
    DBHelper dbHelper;
    private final int GALLERY_REQUEST = 1000;
    private final int STORAGE_PERMISSION_CODE = 101;



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQUEST && data != null){
                Uri imageURI = data.getData();
                if (imageURI != null){
                    profilePicture.setImageURI(imageURI);
                }
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        dbHelper = new DBHelper(getActivity());

        profilePicture = view.findViewById(R.id.profilePicture);
        changePFP = view.findViewById(R.id.changePFP);
        userName = view.findViewById(R.id.userName_Text);

        // Sets the username to the found username from the database
        String username = dbHelper.getUserName();
        if (username != null){
            userName.setText(username);
        } else {
            userName.setText("Username Not found");
        }

        changePFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                } else {
                    Log.d("ProfileFragment", "Gallery permission already granted");
                    openGallery();
                }
            }
        });



        return view;
    }
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            } else {
                Toast.makeText(getActivity(), "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}