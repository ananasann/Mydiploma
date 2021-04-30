package com.hfad.mydiploma;

import com.google.gson.annotations.SerializedName;
import com.hfad.mydiploma.dataTheory.pager.CardData;

import java.util.List;

public class AuthAcc {

    @SerializedName("idTokenAcc")
    String idTokenAcc;

    public AuthAcc(String idTokenAcc) {
        this.idTokenAcc = idTokenAcc;
    }

    public String getidTokenAcc() {
        return idTokenAcc;
    }
}