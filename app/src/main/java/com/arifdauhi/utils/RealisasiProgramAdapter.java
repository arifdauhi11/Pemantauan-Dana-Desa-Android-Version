package com.arifdauhi.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arifdauhi.pdd.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class RealisasiProgramAdapter extends RecyclerView.Adapter<RealisasiProgramAdapter.RealisasiProgramViewHolder> {

    private Context context;
    private RealisasiProgram[] data;

    public RealisasiProgramAdapter(Context context, RealisasiProgram[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RealisasiProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_program_realisasi,parent,false);
        return new RealisasiProgramAdapter.RealisasiProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealisasiProgramViewHolder holder, int position) {
        Double anggaran;
        DecimalFormat kursIndo;
        DecimalFormatSymbols formatRp;
        final RealisasiProgram realisasiProgram = data[position];
        holder.tvProgram.setText(realisasiProgram.getProgram());
        holder.tvTahun.setText(realisasiProgram.getTahun());
        holder.tvPersentase.setText(realisasiProgram.getPersentase());
        holder.llParent.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));
        holder.cvProgram.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));
        holder.llProg.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        String semula, menjadi;
        semula = realisasiProgram.getSemula();
        menjadi = realisasiProgram.getMenjadi();
        if (menjadi.equals("0")){
            anggaran = Double.parseDouble(String.valueOf(semula));
            kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setGroupingSeparator('.');
            formatRp.setMonetaryDecimalSeparator('.');
            kursIndo.setDecimalFormatSymbols(formatRp);
            holder.tvAnggaran.setText(String.valueOf(kursIndo.format(anggaran)));
        } else {
            anggaran = Double.parseDouble(String.valueOf(menjadi));
            kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setGroupingSeparator('.');
            formatRp.setMonetaryDecimalSeparator('.');
            kursIndo.setDecimalFormatSymbols(formatRp);
            holder.tvAnggaran.setText(String.valueOf(kursIndo.format(anggaran)));
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RealisasiProgramViewHolder extends RecyclerView.ViewHolder {
        TextView tvProgram, tvTahun, tvAnggaran, tvPersentase;
        LinearLayout llParent, llProg;
        CardView cvProgram;
        public RealisasiProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProgram = itemView.findViewById(R.id.tvRealisasiProgram);
            tvTahun = itemView.findViewById(R.id.tvRealisasiTahun);
            tvPersentase = itemView.findViewById(R.id.tvRealisasiPersentase);
            tvAnggaran = itemView.findViewById(R.id.tvRealisasiAnggaran);
            llParent = itemView.findViewById(R.id.llparentProgram);
            llProg = itemView.findViewById(R.id.llItemP);
            cvProgram = itemView.findViewById(R.id.cvProgram);
        }
    }
}
