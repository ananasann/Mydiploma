package com.hfad.mydiploma;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MessagesApi {

    @GET("login")             // имя вызываемого метода на сервере - messages1.json - то что нужно добавить к базовому url
    Call<List<Message>> messages();   //List<Product> - это тип возвращаемых данных, messages - имя метода
}
