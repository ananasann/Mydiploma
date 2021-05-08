package com.hfad.mydiploma;

import com.google.gson.annotations.SerializedName;

public class GradeBody {
    @SerializedName("grade")
    String grade;

    @SerializedName("token")
    String token;

    public GradeBody(String grade, String token) {
        this.grade = grade;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getGrade() {
        return grade;
    }
}

