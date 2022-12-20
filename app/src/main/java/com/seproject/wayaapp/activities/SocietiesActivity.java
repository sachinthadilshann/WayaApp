package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.seproject.wayaapp.R;
import com.seproject.wayaapp.adapter.SocietyAdapter;
import com.seproject.wayaapp.model.Society;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SocietiesActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    FirebaseUser user;
    String userId;
    Boolean isAdmin;

    private RecyclerView societyRV;
    private Button createBtn;
    private List<Society> list;
    private SocietyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_societies);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        userId = fAuth.getCurrentUser().getUid();

        fStore.collection("users")
                .document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            isAdmin = task.getResult().getBoolean("isAdmin");
                            if(!isAdmin){
                                createBtn.setVisibility(View.GONE);
                            }

                        }
                    }
                });

        createBtn = findViewById(R.id.create_society_btn);
        societyRV = findViewById(R.id.socirty_RView);


        list = new ArrayList<>();
        adapter = new SocietyAdapter(this,list);
        societyRV.setLayoutManager(new LinearLayoutManager(this));
        societyRV.setAdapter(adapter);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            fStore.collection("societies")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){

                                for(DocumentSnapshot snapshot : Objects.requireNonNull(task).getResult()){
                                    list.add(new Society(
                                            snapshot.getString("name"),
                                            snapshot.getString("id")
                                    ));
                                }

                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(SocietiesActivity.this,"No Society found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        createBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),CreateSocietyActivity.class)));
    }
}