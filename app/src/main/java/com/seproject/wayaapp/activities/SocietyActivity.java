package com.seproject.wayaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class SocietyActivity extends AppCompatActivity {

    private ImageView image;
    private TextView sName, lName;
    private FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society);

        firebaseFirestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        image = findViewById(R.id.societyDp);
        sName = findViewById(R.id.societySname);
        lName = findViewById(R.id.societyLname);

        String sn = getIntent().getStringExtra("sname");
        String ln = getIntent().getStringExtra("lname");

        sName.setText(sn);
        lName.setText(ln);

        StorageReference profileRef = storageReference.child("societies/"+ sn +"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image);
            }
        });


    }
}