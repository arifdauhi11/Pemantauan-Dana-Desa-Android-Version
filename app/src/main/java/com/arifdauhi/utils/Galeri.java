package com.arifdauhi.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Galeri {

    @SerializedName("nama_bidang")
    @Expose
    private String namaBidang;
    @SerializedName("id_gambar")
    @Expose
    private String idGambar;
    @SerializedName("nama_gambar")
    @Expose
    private String namaGambar;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getNamaBidang() {
        return namaBidang;
    }

    public void setNamaBidang(String namaBidang) {
        this.namaBidang = namaBidang;
    }

    public String getIdGambar() {
        return idGambar;
    }

    public void setIdGambar(String idGambar) {
        this.idGambar = idGambar;
    }

    public String getNamaGambar() {
        return namaGambar;
    }

    public void setNamaGambar(String namaGambar) {
        this.namaGambar = namaGambar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
