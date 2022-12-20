package com.seproject.wayaapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.seproject.wayaapp.R;
import com.seproject.wayaapp.model.Section;
import com.seproject.wayaapp.model.SectionRating;

import java.util.List;

public class SectionRatingAdapter extends RecyclerView.Adapter<SectionRatingAdapter.ViewHolder> {
    private Context context;
    private List<SectionRating> list;

    FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private StorageReference storageReference;

    public SectionRatingAdapter(Context context, List<SectionRating> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SectionRatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_rating_layout,parent,false);
        return new SectionRatingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.section.setText(list.get(position).getSection());
        holder.rating.setText(String.valueOf(list.get(position).getRating()));

        fAuth = FirebaseAuth.getInstance();

        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getRating()<5){
                    list.get(position).setRating(list.get(position).getRating()+1);

                    holder.rating.setText(String.valueOf(list.get(position).getRating()));
                }
            }
        });
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getRating()>1){
                    list.get(position).setRating(list.get(position).getRating()-1);

                    holder.rating.setText(String.valueOf(list.get(position).getRating()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView section,rating;
        private Button up,down;
        private CardView cView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            section = itemView.findViewById(R.id.sectionName);
            rating = itemView.findViewById(R.id.rating);
            up = itemView.findViewById(R.id.rateup);
            down = itemView.findViewById(R.id.ratedown);
            cView = itemView.findViewById(R.id.sectionRating);
        }
    }
}
