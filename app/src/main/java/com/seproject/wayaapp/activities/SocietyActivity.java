package com.seproject.wayaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.Objects;

public class SocietyActivity extends AppCompatActivity {

    private ImageView image;
    private TextView sName, lName;
    private FirebaseFirestore firebaseFirestore;
    private RelativeLayout events, jobs, messages, about, members;
    private Button createEvent, sendMsg, postJob,addMe;
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
        events = findViewById(R.id.events1);
        jobs = findViewById(R.id.jobs1);
        messages = findViewById(R.id.messages1);
        about = findViewById(R.id.about11);
        members = findViewById(R.id.members1);
        createEvent = findViewById(R.id.createEventBtn);
        sendMsg = findViewById(R.id.sendMsgBtn);
        postJob = findViewById(R.id.postJobBtn);
        addMe = findViewById(R.id.addMemberBtn);

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

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EventsActivity.class);
                intent.putExtra("sname",sn);
                v.getContext().startActivity(intent);
            }
        });

        members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MembersActivity.class);
                intent.putExtra("sname",sn);
                v.getContext().startActivity(intent);
            }
        });

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateEventActivity.class);
                intent.putExtra("sname",sn);
                v.getContext().startActivity(intent);
            }
        });

        addMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddMemberActivity.class);
                intent.putExtra("sname",sn);
                v.getContext().startActivity(intent);
            }
        });



    }
}