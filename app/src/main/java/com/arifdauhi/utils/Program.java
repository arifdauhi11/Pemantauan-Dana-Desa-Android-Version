package com.arifdauhi.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Program {

    @SerializedName("id_detail_program")
    @Expose
    private String idDetailProgram;
    @SerializedName("detail_program")
    @Expose
    private String detailProgram;

    public String getIdDetailProgram() {
        return idDetailProgram;
    }

    public void setIdDetailProgram(String idDetailProgram) {
        this.idDetailProgram = idDetailProgram;
    }

    public String getDetailProgram() {
        return detailProgram;
    }

    public void setDetailProgram(String detailProgram) {
        this.detailProgram = detailProgram;
    }

}