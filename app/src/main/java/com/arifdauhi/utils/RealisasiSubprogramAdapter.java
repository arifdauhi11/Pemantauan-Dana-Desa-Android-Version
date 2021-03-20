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

import com.android.volley.toolbox.StringRequest;
import com.arifdauhi.pdd.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class RealisasiSubprogramAdapter extends RecyclerView.Adapter<RealisasiSubprogramAdapter.RealisasiSubprogramViewHolder> {

    private Context context;
    private RealisasiSubprogram[] data;

    public RealisasiSubprogramAdapter(Context context, RealisasiSubprogram[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RealisasiSubprogramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_subprogram_realisasi, parent, false);
        return new RealisasiSubprogramAdapter.RealisasiSubprogramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealisasiSubprogramViewHolder holder, int position) {
        Double anggaran;
        DecimalFormat kursIndo;
        DecimalFormatSymbols formatRp;
        final RealisasiSubprogram subprogram = data[position];
        holder.tvSubprogram.setText(subprogram.getRealisasi());
        holder.tvTahun.setText(subprogram.getTahun());
        holder.tvBulan.setText(subprogram.getBulan());
        holder.llParent.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));
        holder.cvSub.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));
        holder.llSub.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        String semula, menjadi;
        semula = subprogram.getSemula();
        menjadi = subprogram.getMenjadi();

        SharedPreferences preferences;
        preferences = context.getSharedPreferences("program", Context.MODE_PRIVATE);
        String jumlahProgram = preferences.getString("jumlahProgram","");
        Integer jumlahParse = Integer.parseInt(jumlahProgram);
        if (menjadi.equals("0")){
            anggaran = Double.parseDouble(String.valueOf(semula));
            kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setGroupingSeparator('.');
            formatRp.setMonetaryDecimalSeparator('.');
            kursIndo.setDecimalFormatSymbols(formatRp);
            holder.tvAnggaranSub.setText(String.valueOf(kursIndo.format(anggaran)));
            Double kali = anggaran * 100;
            Double persentase = kali / jumlahParse;
            holder.tvPersentase.setText(String.valueOf(Math.round(persentase)+"%"));
        } else {
            anggaran = Double.parseDouble(String.valueOf(menjadi));
            kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setGroupingSeparator('.');
            formatRp.setMonetaryDecimalSeparator('.');
            kursIndo.setDecimalFormatSymbols(formatRp);
            holder.tvAnggaranSub.setText(String.valueOf(kursIndo.format(anggaran)));
            Double kali = anggaran * 100;
            Double persentase = kali / jumlahParse;
            holder.tvPersentase.setText(String.valueOf(Math.round(persentase)+"%"));
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RealisasiSubprogramViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubprogram, tvTahun, tvBulan, tvAnggaranSub, tvSerapan, tvSisa,tvPersentase;
        LinearLayout llParent, llSub;
        CardView cvSub;
        public RealisasiSubprogramViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubprogram = itemView.findViewById(R.id.tvRealisasiSubProgram);
            tvTahun = itemView.findViewById(R.id.tvRealisasiTahunSub);
            tvBulan = itemView.findViewById(R.id.tvRealisasiBulanSub);
            tvPersentase = itemView.findViewById(R.id.tvRealisasiPersentaseSub);
            tvAnggaranSub = itemView.findViewById(R.id.tvRealisasiAnggaranSub);
            tvSerapan = itemView.findViewById(R.id.tvRealisasiAnggaranSubSerapan);
            tvSisa = itemView.findViewById(R.id.tvRealisasiAnggaranSubSisa);
            llParent = itemView.findViewById(R.id.llparetSub);
            llSub = itemView.findViewById(R.id.llSub);
            cvSub = itemView.findViewById(R.id.cvSubProgram);
        }
    }
}
