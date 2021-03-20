package com.arifdauhi.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifdauhi.pdd.ProgramActivity;
import com.arifdauhi.pdd.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PendapatanAdapter extends RecyclerView.Adapter<PendapatanAdapter.PendapatanViewHolder> {

    private Context context;
    private Pendapatan[] data;

    public PendapatanAdapter(Context context, Pendapatan[] data){

        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public PendapatanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pendapatan,parent,false);
        return new PendapatanAdapter.PendapatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendapatanViewHolder holder, int position) {
        final Pendapatan pendapatan = data[position];

        holder.txtPendapatan.setText(pendapatan.getSumberPendapatan());
        holder.txtAnggaranSemula.setText(pendapatan.getAnggaran());
        holder.semula = Double.parseDouble(holder.txtAnggaranSemula.getText().toString());
        holder.kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        holder.formatRp = new DecimalFormatSymbols();

        holder.formatRp.setCurrencySymbol("Rp. ");
        holder.formatRp.setMonetaryDecimalSeparator('.');
        holder.formatRp.setGroupingSeparator('.');
        holder.kursIndo.setDecimalFormatSymbols(holder.formatRp);
        holder.txtAnggaran.setText(String.valueOf(holder.kursIndo.format(holder.semula)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ProgramActivity.class);
//                intent.putExtra("id",pendapatan.getIdPendapatan());
//                context.startActivity(intent);
                Toast.makeText(context, Integer.parseInt(pendapatan.getIdPendapatan()) + Integer.parseInt(pendapatan.getIdPendapatan()) +" Clicked", Toast.LENGTH_LONG).show();
//                Animatoo.animateSlideLeft(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class PendapatanViewHolder extends RecyclerView.ViewHolder{

        TextView txtPendapatan, txtAnggaranSemula, txtAnggaran;
        Double semula;
        DecimalFormat kursIndo;
        DecimalFormatSymbols formatRp;

        public PendapatanViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPendapatan = itemView.findViewById(R.id.itemPendapatan);
            txtAnggaranSemula = itemView.findViewById(R.id.itemAnggaranPendapatanSemula);
            txtAnggaran = itemView.findViewById(R.id.itemAnggaranPendapatan);
        }
    }
}
