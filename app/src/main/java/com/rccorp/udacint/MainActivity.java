package com.rccorp.udacint;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {

    ViewPager mainView;

    List<String> fname=new ArrayList<>();
    List<String> lname=new ArrayList<>();
    List<String> avalist=new ArrayList<>();


    CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = (ViewPager) findViewById(R.id.mainViewPager);

        getCardData();

        fname.add("Sumit");
        lname.add("birud");
        fname.add("Rutwik");
        lname.add("Chinchole");
        avalist.add("https://cdn1.iconfinder.com/data/icons/ninja-things-1/1772/ninja-simple-512.png");
        avalist.add("https://t4.ftcdn.net/jpg/00/78/73/53/240_F_78735333_o3qJe4bT5ciwldLIjVDulFKrDAV3jGYO.jpg");


        mCardAdapter=new CardAdapter();
        for (int i=0;i<fname.size();i++){
            mCardAdapter.addCardItem(new Roloitem(fname.get(i),lname.get(i),avalist.get(i)));
        }

        mainView.setAdapter(mCardAdapter);

    }


    public static final String BASE_URL = "http://s3-us-west-2.amazonaws.com/";

    public void getCardData() {


            Retrofit adapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = adapter.create(Api.class);

            Call<Rolodata> call = api.getData();

            call.enqueue(new Callback<Rolodata>() {
                @Override
                public void onResponse(Call<Rolodata> call, Response<Rolodata> response) {

                    String firstName=response.body().getFirstName();
                    String lastName=response.body().getLastname();
                    String avatar=response.body().getAvatar();
                    fname.add(firstName);
                    lname.add(lastName);
                    avalist.add(avatar);
                    Log.e("Success", "getData: onResponse " + response.message());
                }

                @Override
                public void onFailure(Call<Rolodata> call, Throwable t) {
                    Log.e("Failure", "getData: onResponse ");
                }
            });

    }

}





