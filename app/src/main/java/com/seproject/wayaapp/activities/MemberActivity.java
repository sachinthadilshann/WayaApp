package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;
import com.seproject.wayaapp.adapter.SectionAdapter;
import com.seproject.wayaapp.adapter.SectionRatingAdapter;
import com.seproject.wayaapp.model.Section;
import com.seproject.wayaapp.model.SectionRating;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberActivity extends AppCompatActivity {

    private TextView name;
    private ImageView dp;
    private RecyclerView sectionRv1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String socName, memberId,memName;

    private List<SectionRating> list;
    private SectionRatingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        sectionRv1 = findViewById(R.id.sectionsrateRV);
        dp = findViewById(R.id.dp);
        name = findViewById(R.id.memName);

        list = new ArrayList<>();
        adapter = new SectionRatingAdapter(this,list);
        sectionRv1.setLayoutManager(new LinearLayoutManager(this));
        sectionRv1.setAdapter(adapter);

        socName = getIntent().getStringExtra("societyname");
        memberId = getIntent().getStringExtra("Id");
        memName = getIntent().getStringExtra("name");

        name.setText(memName);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+ memberId+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(dp);
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            fStore.collection("societies/"+socName+"/members/"+memberId+"/sections")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){

                                for(DocumentSnapshot snapshot : Objects.requireNonNull(task).getResult()){
                                    list.add(new SectionRating(
                                            snapshot.getString("id"),
                                            Integer.parseInt(snapshot.getString("rating"))
                                    ));
                                }

                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(MemberActivity.this,"No section found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}