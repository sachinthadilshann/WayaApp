package com.seproject.wayaapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TimePicker;
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
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class CreateEventActivity extends AppCompatActivity {
    EditText id, title,desc;
    Button createEventBtn,expBtn;
    ImageView flyer;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    String society;
    int dateTime[] = {2022,11,21,9,0};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        title = findViewById(R.id.eventTitle);
        desc = findViewById(R.id.eventDesc);
        createEventBtn = findViewById(R.id.event_submit_btn);
        flyer = findViewById(R.id.eventimg);
        expBtn = findViewById(R.id.expBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        progressBar = findViewById(R.id.progressBar3);

        society = getIntent().getStringExtra("sname");

        flyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().isEmpty()){
                    Toast.makeText(CreateEventActivity.this, "Enter Event Title First", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });

        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ttitle = title.getText().toString();
                final String ddesc = desc.getText().toString();

                if(ttitle.isEmpty() || ddesc.isEmpty()){
                    Toast.makeText(CreateEventActivity.this, "One or Many fields are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ttitle.length()>141 ){
                    Toast.makeText(CreateEventActivity.this, "Title is too long.", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility((View.VISIBLE));
                final String finalid = removeNonAlphanumeric(ttitle);

                DocumentReference docRef = fStore.collection("societies/"+ society +"/events/").document(finalid);
                Map<String,Object> edited = new HashMap<>();
                edited.put("id",finalid);
                edited.put("title",ttitle);
                edited.put("desc",ddesc);
                docRef.set(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateEventActivity.this, "Event Created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                Toast.makeText(CreateEventActivity.this, "Successful.", Toast.LENGTH_SHORT).show();


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
        final String finalid = removeNonAlphanumeric(title.getText().toString());
        final StorageReference fileRef = storageReference.child("societies/"+society+"/"+finalid+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(flyer);
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
        if(str.length()>32)
            str = str.substring(0,30);
        return str;
    }

    public void setExpire(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateTime[3]=hourOfDay;
                dateTime[4]=minute;
                expBtn.setText(String.format(Locale.getDefault(),"Exp. on %04d-%02d-%02d %02d:%02d",dateTime[0],dateTime[1],dateTime[2],dateTime[3],dateTime[4]));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,dateTime[3],dateTime[4],true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateTime[0]=year;
                dateTime[1]=month;
                dateTime[2]=dayOfMonth;
                expBtn.setText(String.format(Locale.getDefault(),"Exp. on %04d-%02d-%02d %02d:%02d",dateTime[0],dateTime[1],dateTime[2],dateTime[3],dateTime[4]));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,onDateSetListener,dateTime[0],dateTime[1],dateTime[2]);
        datePickerDialog.setTitle("SelectDate");
        datePickerDialog.show();


    }
}