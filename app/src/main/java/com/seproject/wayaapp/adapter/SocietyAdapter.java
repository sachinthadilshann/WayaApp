package com.seproject.wayaapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;
import com.seproject.wayaapp.activities.SocietyActivity;
import com.seproject.wayaapp.model.Society;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class SocietyAdapter extends RecyclerView.Adapter<SocietyAdapter.ViewHolder> {

    private Context context;
    private List<Society> list;

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private StorageReference storageReference;

    public SocietyAdapter(Context context, List<Society> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.society_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.societyShortname.setText(list.get(position).getId());
        holder.societyLongname.setText(list.get(position).getName());

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        storageReference = FirebaseStorage.getInstance().getReference();


        StorageReference profileRef = storageReference.child("societies/"+ list.get(position).getId()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.societyDp);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SocietyActivity.class);
                intent.putExtra("sname",list.get(position).getId());
                intent.putExtra("lname",list.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView societyDp;
        private TextView societyShortname,societyLongname;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            societyDp = itemView.findViewById(R.id.societyDp);
            societyLongname = itemView.findViewById(R.id.societyLname);
            societyShortname = itemView.findViewById(R.id.societySname);

            cardView = itemView.findViewById(R.id.card_view);

        }
    }
}
