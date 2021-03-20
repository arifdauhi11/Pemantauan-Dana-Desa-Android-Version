package com.arifdauhi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arifdauhi.pdd.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class RealisasiSubprogramTahunAdapter extends RecyclerView.Adapter<RealisasiSubprogramTahunAdapter.RealisasiSubprogramTahunViewHolder> {

    private Context context;
    private RealisasiSubprogramTahun[] data;

    public RealisasiSubprogramTahunAdapter(Context context, RealisasiSubprogramTahun[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RealisasiSubprogramTahunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_subprogram_realisasi_tahun, parent, false);
        return new RealisasiSubprogramTahunAdapter.RealisasiSubprogramTahunViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealisasiSubprogramTahunViewHolder holder, int position) {
        final RealisasiSubprogramTahun subprogram = data[position];
        holder.tvSubprogram.setText(subprogram.getRealisasi());
        holder.tvTahun.setText(subprogram.getTahun());
        holder.llParent.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));
        holder.cvSub.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));
        holder.llSub.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        String semula, menjadi;
        semula = subprogram.getAsemula();
        menjadi = subprogram.getAmenjadi();
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("program", Context.MODE_PRIVATE);
        String jumlahProgram = preferences.getString("jumlahProgram","");
        Integer jumlahParse = Integer.parseInt(jumlahProgram);
//        Toast.makeText(context, String.valueOf(jumlahParse), Toast.LENGTH_SHORT).show();
        if (!menjadi.equals("0")){
            holder.tvAnggaranSub.setText(subprogram.getMenjadi());
            Double kali = Double.parseDouble(menjadi) * 100;
            Double persentase = kali / jumlahParse;
            holder.tvPersentase.setText(String.valueOf(Math.round(persentase)+"%"));
//            Toast.makeText(context, String.valueOf(persentase), Toast.LENGTH_SHORT).show();
        } else {
            holder.tvAnggaranSub.setText(subprogram.getSemula());
            Double kali = Double.parseDouble(semula) * 100;
            Double persentase = kali / jumlahParse;
            holder.tvPersentase.setText(String.valueOf(Math.round(persentase)+"%"));
//            Toast.makeText(context, String.valueOf(persentase), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RealisasiSubprogramTahunViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubprogram, tvTahun, tvAnggaranSub, tvPersentase;
        LinearLayout llParent, llSub;
        CardView cvSub;
        public RealisasiSubprogramTahunViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubprogram = itemView.findViewById(R.id.tvRealisasiSubProgramPertahun);
            tvTahun = itemView.findViewById(R.id.tvRealisasiTahunSubPertahun);
            tvPersentase = itemView.findViewById(R.id.tvRealisasiPersentaseSubPertahun);
            tvAnggaranSub = itemView.findViewById(R.id.tvRealisasiAnggaranSubPertahun);
            llParent = itemView.findViewById(R.id.llparetSubPertahun);
            llSub = itemView.findViewById(R.id.llSubPertahun);
            cvSub = itemView.findViewById(R.id.cvSubProgramPertahun);
        }
    }
}
