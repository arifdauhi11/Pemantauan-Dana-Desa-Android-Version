package com.arifdauhi.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Saran {
    @SerializedName("id_saran")
    @Expose
    private String idSaran;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("saran")
    @Expose
    private String saran;
    @SerializedName("status")
    @Expose
    private String status;

    public String getIdSaran() {
        return idSaran;
    }

    public void setIdSaran(String idSaran) {
        this.idSaran = idSaran;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
