package com.arifdauhi.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealisasiProgram {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("tahun")
    @Expose
    private String tahun;
    @SerializedName("semula")
    @Expose
    private String semula;
    @SerializedName("menjadi")
    @Expose
    private String menjadi;
    @SerializedName("persentase")
    @Expose
    private String persentase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    public String getPersentase() {
        return persentase;
    }

    public void setPersentase(String persentase) {
        this.persentase = persentase;
    }
}
