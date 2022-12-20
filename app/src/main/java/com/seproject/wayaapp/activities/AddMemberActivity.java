package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;
import com.seproject.wayaapp.adapter.EventAdapter;
import com.seproject.wayaapp.adapter.MemberAdapter;
import com.seproject.wayaapp.adapter.SectionAdapter;
import com.seproject.wayaapp.model.Event;
import com.seproject.wayaapp.model.Member;
import com.seproject.wayaapp.model.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddMemberActivity extends AppCompatActivity {

    private Button submit;
    private RecyclerView sectionRv;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String society,name;
    String userId;
    Query isMember;

    private List<Section> list;
    private SectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        userId = fAuth.getCurrentUser().getUid();

        CollectionReference societyRef = fStore.collection("societies/"+society+"/members");
        isMember = societyRef.whereArrayContains("id",userId);
        isMember.equals(userId);

        submit = findViewById(R.id.addMeBtn);
        sectionRv = findViewById(R.id.sectionsRV);

        list = new ArrayList<>();
        adapter = new SectionAdapter(this,list);
        sectionRv.setLayoutManager(new LinearLayoutManager(this));
        sectionRv.setAdapter(adapter);

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
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            fStore.collection("societies/"+society+"/sections")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){

                                for(DocumentSnapshot snapshot : Objects.requireNonNull(task).getResult()){
                                    list.add(new Section(
                                            snapshot.getString("id"),
                                            snapshot.getString("name")
                                    ));
                                }

                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(AddMemberActivity.this,"No section found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

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
                        Toast.makeText(AddMemberActivity.this, "Request Sent", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                for (int i=0;i<list.size();i++){
                    docRef = fStore.collection("societies/"+ society +"/members/"+user.getUid()+"/sections").document(list.get(i).getId());
                    edited.clear();
                    if (list.get(i).isValue()==true){
                        edited.put("id",list.get(i).getId());
                        edited.put("rating","0");
                    docRef.set(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            return;
                        }
                    });}
                }
                Toast.makeText(AddMemberActivity.this, "Successful.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}