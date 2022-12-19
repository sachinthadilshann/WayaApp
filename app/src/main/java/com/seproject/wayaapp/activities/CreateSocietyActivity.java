package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;



import com.seproject.wayaapp.R;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateSocietyActivity extends AppCompatActivity {

    EditText id,name,pId, sId;
    Button createSocBtn;
    ImageView societyLogo;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_society);

        id = findViewById(R.id.society_id);
        name = findViewById(R.id.society_name);
        pId = findViewById(R.id.president_id);
        sId = findViewById(R.id.secretary_id);
        createSocBtn = findViewById(R.id.society_submit_btn);
        societyLogo = findViewById(R.id.society_img_upload);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        progressBar = findViewById(R.id.progressBar2);


        societyLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().isEmpty()){
                    Toast.makeText(CreateSocietyActivity.this, "Enter Society Name First", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });


        createSocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nname = name.getText().toString();
                final String ppId = pId.getText().toString();
                final String ssId = sId.getText().toString();
                final String iid = id.getText().toString();

                if(nname.isEmpty() || ppId.isEmpty() || ssId.isEmpty()){
                    Toast.makeText(CreateSocietyActivity.this, "One or Many fields are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!ppId.matches("[0-9]+") || !ssId.matches("[0-9]+") || ppId.length()!=6|| ssId.length()!=6){
                    Toast.makeText(CreateSocietyActivity.this, "One or Many Index numbers are invalid.", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility((View.VISIBLE));
                final String finalid = removeNonAlphanumeric(iid);

                DocumentReference docRef = fStore.collection("societies/").document(finalid);
                Map<String,Object> edited = new HashMap<>();
                edited.put("id",finalid);
                edited.put("name",name.getText().toString());
                edited.put("pId",pId.getText().toString().trim());
                edited.put("sId",sId.getText().toString().trim());
                docRef.set(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateSocietyActivity.this, "Society Created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                Toast.makeText(CreateSocietyActivity.this, "Successful.", Toast.LENGTH_SHORT).show();


            }
        });


    }

    ProgressDialog dialog;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = Objects.requireNonNull(data).getData();
                uploadImageToFirebase(imageUri);
                dialog = new ProgressDialog(this);
                dialog.setMessage("Uploading");


            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        final String finalid = removeNonAlphanumeric(id.getText().toString());
        final StorageReference fileRef = storageReference.child("societies/"+finalid+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(societyLogo);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static String removeNonAlphanumeric(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "");
        return str;
    }
}