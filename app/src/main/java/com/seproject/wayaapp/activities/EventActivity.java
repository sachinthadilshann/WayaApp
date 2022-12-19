package com.seproject.wayaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;
import com.squareup.picasso.Picasso;

public class EventActivity extends AppCompatActivity {

    private ImageView image;
    private TextView title, desc;
    private FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        firebaseFirestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        image = findViewById(R.id.flyer);
        title = findViewById(R.id.eventTitle);
        desc = findViewById(R.id.eventDesc);

        String titile = getIntent().getStringExtra("title");
        String desc1 = getIntent().getStringExtra("desc");
        String sn = getIntent().getStringExtra("societyname");
        String id = getIntent().getStringExtra("eventId");

        title.setText(titile);
        desc.setText(desc1);

        StorageReference profileRef = storageReference.child("societies/"+ sn +"/"+id+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image);
            }
        });
    }
}