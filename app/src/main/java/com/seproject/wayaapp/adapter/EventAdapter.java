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
import com.seproject.wayaapp.activities.EventActivity;
import com.seproject.wayaapp.activities.EventsActivity;
import com.seproject.wayaapp.activities.SocietyActivity;
import com.seproject.wayaapp.model.Event;
import com.seproject.wayaapp.model.Society;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<Event> list;

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private StorageReference storageReference;

    public EventAdapter(Context context, List<Event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_layout,parent,false);
        return new EventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).getTitle());

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        storageReference = FirebaseStorage.getInstance().getReference();


        StorageReference profileRef = storageReference.child("societies/"+ list.get(position).getSociety()+"/" + list.get(position).getId()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.flyer);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("societyname",list.get(position).getSociety());
                intent.putExtra("eventId",list.get(position).getId());
                intent.putExtra("title",list.get(position).getId());
                intent.putExtra("desc",list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView flyer;
        private TextView title;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flyer = itemView.findViewById(R.id.flyer);
            title = itemView.findViewById(R.id.eventTitle);

            cardView = itemView.findViewById(R.id.card_view2);

        }
    }
}
