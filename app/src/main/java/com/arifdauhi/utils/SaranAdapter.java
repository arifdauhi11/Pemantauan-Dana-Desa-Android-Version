package com.arifdauhi.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifdauhi.pdd.R;
import com.squareup.picasso.Picasso;

public class SaranAdapter extends RecyclerView.Adapter<SaranAdapter.SaranViewHolder> {

    private Context context;
    private Saran[] data;

    public SaranAdapter(Context context, Saran[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SaranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_saran, parent, false);
        return new SaranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaranViewHolder holder, int position) {
        final Saran saran = data[position];
        String imgUrl = "https://arifdauhi.tk/assets/images/"+saran.getImage();
        holder.nama.setText("- "+saran.getNama());
        holder.saran.setText(saran.getSaran());
        Picasso.get()
                .load(imgUrl)
                .fit()
                .centerInside()
                .into(holder.ivUser);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class SaranViewHolder extends RecyclerView.ViewHolder {
        TextView saran, nama;
        ImageView ivUser;
        public SaranViewHolder(@NonNull View itemView) {
            super(itemView);
            saran = itemView.findViewById(R.id.tvSaran);
            nama = itemView.findViewById(R.id.tvNamaSaran);
            ivUser = itemView.findViewById(R.id.ivSaran);
        }
    }
}
