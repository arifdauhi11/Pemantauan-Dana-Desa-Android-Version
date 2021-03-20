package com.arifdauhi.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealisasiSubprogram {
    @SerializedName("id_anggaran")
    @Expose
    private String idAnggaran;
    @SerializedName("id_realisasi")
    @Expose
    private String idRealisasi;
    @SerializedName("amenjadi")
    @Expose
    private String amenjadi;
    @SerializedName("asemula")
    @Expose
    private String asemula;
    @SerializedName("realisasi")
    @Expose
    private String realisasi;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("bulan")
    @Expose
    private String bulan;
    @SerializedName("semula")
    @Expose
    private String semula;
    @SerializedName("menjadi")
    @Expose
    private String menjadi;
    @SerializedName("serapan")
    @Expose
    private String serapan;
    @SerializedName("sisa")
    @Expose
    private String sisa;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("anggaran")
    @Expose
    private String anggaran;

    public String getIdAnggaran() {
        return idAnggaran;
    }

    public void setIdAnggaran(String idAnggaran) {
        this.idAnggaran = idAnggaran;
    }

    public String getIdRealisasi() {
        return idRealisasi;
    }

    public void setIdRealisasi(String idRealisasi) {
        this.idRealisasi = idRealisasi;
    }

    public String getAmenjadi() {
        return amenjadi;
    }

    public void setAmenjadi(String amenjadi) {
        this.amenjadi = amenjadi;
    }

    public String getAsemula() {
        return asemula;
    }

    public void setAsemula(String asemula) {
        this.asemula = asemula;
    }

    public String getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(String realisasi) {
        this.realisasi = realisasi;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getSemula() {
        return semula;
    }

    public void setSemula(String semula) {
        this.semula = semula;
    }

    public String getMenjadi() {
        return menjadi;
    }

    public void setMenjadi(String menjadi) {
        this.menjadi = menjadi;
    }

    public String getSerapan() {
        return serapan;
    }

    public void setSerapan(String serapan) {
        this.serapan = serapan;
    }

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getAnggaran() {
        return anggaran;
    }

    public void setAnggaran(String anggaran) {
        this.anggaran = anggaran;
    }
}
