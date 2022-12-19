package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.seproject.wayaapp.R;
import com.seproject.wayaapp.adapter.MemberAdapter;
import com.seproject.wayaapp.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembersActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    FirebaseUser user;
    String userId;
    private  String societyName;

    private RecyclerView memberRV, newReqRv;
    private List<Member> list,newList;
    private MemberAdapter adapter,newAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        userId = fAuth.getCurrentUser().getUid();

        memberRV = findViewById(R.id.Members_RView);
        newReqRv = findViewById(R.id.newReq_RView);

        list = new ArrayList<>();
        adapter = new MemberAdapter(this,list);
        memberRV.setLayoutManager(new LinearLayoutManager(this));
        memberRV.setAdapter(adapter);

        newList = new ArrayList<>();
        newAdapter = new MemberAdapter(this,newList);
        newReqRv.setLayoutManager(new LinearLayoutManager(this));
        newReqRv.setAdapter(newAdapter);

        societyName = getIntent().getStringExtra("sname");

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            fStore.collection("societies/"+societyName+"/members")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){

                                for(DocumentSnapshot snapshot : Objects.requireNonNull(task).getResult()){

                                    if(snapshot.getBoolean("valid")){
                                        list.add(new Member(
                                                societyName,
                                                snapshot.getString("id"),
                                                snapshot.getString("name"),
                                                snapshot.getBoolean("valid")
                                        ));
                                        adapter.notifyDataSetChanged();
                                    }
                                    else{
                                        newList.add(new Member(
                                                societyName,
                                                snapshot.getString("id"),
                                                snapshot.getString("name"),
                                                snapshot.getBoolean("valid")
                                        ));
                                        newAdapter.notifyDataSetChanged();
                                    }
                                }


                            }else{
                                Toast.makeText(MembersActivity.this,"No Members found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


}