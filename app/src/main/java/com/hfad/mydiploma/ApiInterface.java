package com.hfad.mydiploma;

import com.hfad.mydiploma.dataTheory.TheoryCard;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("themes")             // имя вызываемого метода на сервере - messages1.json - то что нужно добавить к базовому url
    Call<List<TheoryCard>> getTheor();   //List<Product> - это тип возвращаемых данных, messages - имя метода

    @POST("tokensignin")
    Call<List<AuthAcc>> postAuthAcc(@Body List<AuthAcc> body);
}
