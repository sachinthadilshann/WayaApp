package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.seproject.wayaapp.adapter.EventAdapter;
import com.seproject.wayaapp.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventsActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    FirebaseUser user;
    String userId;
    private  String sname;

    private RecyclerView eventRV;
    private List<Event> list;
    private EventAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        userId = fAuth.getCurrentUser().getUid();

        eventRV = findViewById(R.id.Events_RView);

        list = new ArrayList<>();
        adapter = new EventAdapter(this,list);
        eventRV.setLayoutManager(new LinearLayoutManager(this));
        eventRV.setAdapter(adapter);

        sname = getIntent().getStringExtra("sname");

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            fStore.collection("societies/"+sname+"/events")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){

                                for(DocumentSnapshot snapshot : Objects.requireNonNull(task).getResult()){
                                    list.add(new Event(
                                            sname,
                                            snapshot.getString("title"),
                                            snapshot.getString("desc"),
                                            snapshot.getString("id"),
                                            snapshot.getString("exp")
                                    ));
                                }

                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(EventsActivity.this,"No Events found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


}