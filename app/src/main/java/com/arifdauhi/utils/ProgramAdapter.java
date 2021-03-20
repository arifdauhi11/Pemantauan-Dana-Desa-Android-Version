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

import com.arifdauhi.pdd.R;
import com.arifdauhi.pdd.RealisasiActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {
    private Context context;
    private Program[] data;

    public ProgramAdapter(Context context, Program[] data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_program,parent,false);
        return new ProgramAdapter.ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
        final Program program = data[position];
        holder.tvNama.setText(program.getDetailProgram());
        holder.tvNama.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.llprogram.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RealisasiActivity.class);
                intent.putExtra("id", program.getIdDetailProgram());
                context.startActivity(intent);
                Animatoo.animateSlideLeft(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama;
        LinearLayout llprogram;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            llprogram = itemView.findViewById(R.id.llProgram);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }
}
