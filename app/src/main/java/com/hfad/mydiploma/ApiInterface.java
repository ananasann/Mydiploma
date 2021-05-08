package com.hfad.mydiploma;

import com.google.gson.annotations.SerializedName;
import com.hfad.mydiploma.dataTests.TestsCard;
import com.hfad.mydiploma.dataTheory.TheoryCard;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("themes")
    Call<List<TheoryCard>> getTheor();

    @POST("tokensignin")
    Call<List<AuthAcc>> postAuthAcc(@Body List<AuthAcc> body);

    @GET("testsp")
    Call<List<TestsCard>> getTest();

    @POST("grade")
    Call<Void> postGrade(@Body GradeBody body);
}
