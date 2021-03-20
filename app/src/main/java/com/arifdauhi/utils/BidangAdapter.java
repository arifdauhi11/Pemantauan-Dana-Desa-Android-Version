package com.arifdauhi.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifdauhi.pdd.BidangActivity;
import com.arifdauhi.pdd.KontakActivity;
import com.arifdauhi.pdd.LoginActivity;
import com.arifdauhi.pdd.ProgramActivity;
import com.arifdauhi.pdd.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class BidangAdapter extends RecyclerView.Adapter<BidangAdapter.BidangViewHolder> {

    private Context context;
    private Bidang[] data;

    public BidangAdapter(Context context, Bidang[] data){

        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public BidangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bidang,parent,false);
        return new BidangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BidangViewHolder holder, int position) {
        final Bidang bidang = data[position];
        holder.txtBidang.setText(bidang.getBidang());
        holder.txtIdBidang.setText(bidang.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("id",bidang.getId());
                context.startActivity(intent);
                Animatoo.animateSlideLeft(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class BidangViewHolder extends RecyclerView.ViewHolder{

        TextView txtBidang;
        TextView txtIdBidang;

        public BidangViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBidang = itemView.findViewById(R.id.txtBidang);
            txtIdBidang = itemView.findViewById(R.id.txtId);
        }
    }

}
