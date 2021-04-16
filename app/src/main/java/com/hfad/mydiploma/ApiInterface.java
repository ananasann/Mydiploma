package com.hfad.mydiploma;

import com.hfad.mydiploma.dataTheory.TheoryCard;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("test")             // имя вызываемого метода на сервере - messages1.json - то что нужно добавить к базовому url
    Call<List<TheoryCard>> getTheor();   //List<Product> - это тип возвращаемых данных, messages - имя метода
}
