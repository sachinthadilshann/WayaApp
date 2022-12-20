package com.seproject.wayaapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;
import com.seproject.wayaapp.activities.MemberActivity;
import com.seproject.wayaapp.model.Member;
import com.seproject.wayaapp.model.Section;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder>{
    private Context context;
    private List<Section> list;

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private StorageReference storageReference;

    public SectionAdapter(Context context, List<Section> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_layout,parent,false);
        return new SectionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(list.get(position).getId());

        fAuth = FirebaseAuth.getInstance();

        holder.addRemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addRemBtn.setVisibility(View.GONE);
                list.get(position).setValue(true);

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private CardView cardView;
        private Button addRemBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.sectionName);
            cardView = itemView.findViewById(R.id.sectionCard);
            addRemBtn = itemView.findViewById(R.id.addRemove);

        }
    }
}

