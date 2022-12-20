package com.seproject.wayaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.annotation.Nullable;

public class Level1_upload extends AppCompatActivity {

    private static final int GALLERY_INTENT_CODE = 1023 ;
    FirebaseAuth fAuth;
    Uri imageuri = null;
    Button uploadpdf;
    FirebaseUser user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_upload);

        uploadpdf = findViewById(R.id.uploadpdf);

        fAuth = FirebaseAuth.getInstance();

        userId = fAuth.getCurrentUser().getUid();


            ImageView upload;
            Uri imageuri = null;


            uploadpdf.setOnClickListener(v -> {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

               // galleryIntent.setType("image/*");
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);
            });
    }

    ProgressDialog dialog;




        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {


                dialog = new ProgressDialog(this);
                dialog.setMessage("Uploading");

                dialog.show();
                imageuri = data.getData();
                final String timestamp = "" + System.currentTimeMillis();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                final String messagePushID = timestamp;
                Toast.makeText(Level1_upload.this, imageuri.toString(), Toast.LENGTH_SHORT).show();
                final StorageReference filepath = storageReference.child("users/"+userId+"/ExamApplication.PDF");

                Toast.makeText(Level1_upload.this, filepath.getName(), Toast.LENGTH_SHORT).show();
                filepath.putFile(imageuri).continueWithTask(new Continuation() {
                    @Override
                    public Object then(@NonNull Task task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
                    if (task.isSuccessful()) {

                        dialog.dismiss();
                        Uri uri = task.getResult();
                        String myurl;
                        myurl = uri.toString();
                        Toast.makeText(Level1_upload.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        dialog.dismiss();
                        Toast.makeText(Level1_upload.this, "UploadedFailed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
