package com.arifdauhi.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pendapatan {

    @SerializedName("id_pendapatan")
    @Expose
    private String idPendapatan;
    @SerializedName("sumber_pendapatan")
    @Expose
    private String sumberPendapatan;
    @SerializedName("anggaran_semula")
    @Expose
    private String anggaran;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("id_tahun")
    @Expose
    private String idTahun;

    public String getIdPendapatan() {
        return idPendapatan;
    }

    public void setIdPendapatan(String idPendapatan) {
        this.idPendapatan = idPendapatan;
    }

    public String getSumberPendapatan() {
        return sumberPendapatan;
    }

    public void setSumberPendapatan(String sumberPendapatan) {
        this.sumberPendapatan = sumberPendapatan;
    }

    public String getAnggaran() {
        return anggaran;
    }

    public void setAnggaran(String anggaran) {
        this.anggaran = anggaran;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getIdTahun() {
        return idTahun;
    }

    public void setIdTahun(String idTahun) {
        this.idTahun = idTahun;
    }

}