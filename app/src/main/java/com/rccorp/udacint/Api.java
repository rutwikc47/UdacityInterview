package com.rccorp.udacint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Rutwik on 06/01/18.
 */

public interface Api {

    @GET("udacity-mobile-interview/CardData.json")
    Call<List<Rolodata>> getData();

}
