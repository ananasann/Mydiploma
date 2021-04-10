package com.hfad.mydiploma;

import com.hfad.mydiploma.dataTheory.Theory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TheoryApi {

    @GET("test")             // имя вызываемого метода на сервере - messages1.json - то что нужно добавить к базовому url
    Call<List<Theory>> getTheor();   //List<Product> - это тип возвращаемых данных, messages - имя метода
}
