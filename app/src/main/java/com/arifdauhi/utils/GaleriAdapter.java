package com.arifdauhi.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arifdauhi.pdd.R;
import com.squareup.picasso.Picasso;

public class GaleriAdapter extends RecyclerView.Adapter<GaleriAdapter.GaleriViewHolder>{

    private Context context;
    private Galeri[] data;

    public GaleriAdapter(Context context, Galeri[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GaleriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_galeri,parent,false);
        return new GaleriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriViewHolder holder, int position) {
        final Galeri galeri = data[position];
        String imgUrl = "https://arifdauhi.tk/assets/images/multiple/"+galeri.getNamaGambar();
        holder.galeri.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.ivItemGal.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        holder.tvItemGal.setText(galeri.getNamaBidang());
        holder.tvIdItemGal.setText(galeri.getIdGambar());
        Picasso.get()
                .load(imgUrl)
                .fit()
                .centerInside()
                .into(holder.ivItemGal);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GaleriViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdItemGal,tvItemGal;
        ImageView ivItemGal;
        CardView galeri;
        public GaleriViewHolder(@NonNull View itemView) {
            super(itemView);
            galeri = itemView.findViewById(R.id.cvGaleri);
            tvIdItemGal = itemView.findViewById(R.id.tvIdItemGaleri);
            tvItemGal = itemView.findViewById(R.id.tvItemGaleri);
            ivItemGal = itemView.findViewById(R.id.imageViewItemGaleri);
        }
    }
}
