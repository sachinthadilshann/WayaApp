package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;

import java.util.HashMap;
import java.util.Map;

public class AddMemberActivity extends AppCompatActivity {

    private Button submit;
    private RecyclerView sectionRv;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String society,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        submit = findViewById(R.id.addMeBtn);

        society = getIntent().getStringExtra("sname");

        fStore.collection("users")
                .document(user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                            name = task.getResult().getString("Full Name");
                    }
                });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef = fStore.collection("societies/"+ society +"/members/").document(user.getUid());

                Map<String,Object> edited = new HashMap<>();
                edited.put("id",user.getUid());
                edited.put("name",name);
                edited.put("valid",false);
                docRef.set(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddMemberActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                Toast.makeText(AddMemberActivity.this, "Successful.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}