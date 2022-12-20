package com.seproject.wayaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seproject.wayaapp.R;
import com.seproject.wayaapp.model.Section;

import java.util.List;

public class SectionTextAdapter extends RecyclerView.Adapter<SectionTextAdapter.ViewHolder> {

    private Context context;
    private List<Section> list;

    public SectionTextAdapter(Context context, List<Section> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SectionTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_text_layout,parent,false);
        return new SectionTextAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionTextAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.sectionText);
        }
    }
}
