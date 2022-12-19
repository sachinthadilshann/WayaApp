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
import com.seproject.wayaapp.activities.MemberActivity;
import com.seproject.wayaapp.model.Member;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private Context context;
    private List<Member> list;

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private StorageReference storageReference;

    public MemberAdapter(Context context, List<Member> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MemberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_layout,parent,false);
        return new MemberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).getName());

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+ list.get(position).getId()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.memberDp);
            }
        });

        holder.memberCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MemberActivity.class);
                intent.putExtra("societyname",list.get(position).getSocietyName());
                intent.putExtra("Id",list.get(position).getId());
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("valid",list.get(position).getValid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView memberDp;
        private TextView name;
        //private RecyclerView memberRv;
        private CardView memberCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            memberDp = itemView.findViewById(R.id.memberDp);
            name = itemView.findViewById(R.id.memberName);
            //memberRv = itemView.findViewById(R.id.member_section_rv);

            memberCardView = itemView.findViewById(R.id.member_card_view);

        }
    }
}
