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

        getCardData();

        mainView = (ViewPager) findViewById(R.id.mainViewPager);


    }


    public static final String BASE_URL = "http://s3-us-west-2.amazonaws.com/";

    public void getCardData() {


            Retrofit adapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = adapter.create(Api.class);

            Call<List<Rolodata>> call = api.getData();

            call.enqueue(new Callback<List<Rolodata>>() {
                @Override
                public void onResponse(Call<List<Rolodata>> call, Response<List<Rolodata>> response) {
                    for (int i=0;i<response.body().size();i++){
                        String firstName=response.body().get(i).getFirstName();
                        String lastName=response.body().get(i).getLastname();
                        String avatar=response.body().get(i).getAvatar();
                        fname.add(firstName);
                        lname.add(lastName);
                        avalist.add(avatar);
                    }

                    mCardAdapter=new CardAdapter();
                    for (int i=0;i<fname.size();i++){
                        mCardAdapter.addCardItem(new Roloitem(fname.get(i),lname.get(i),avalist.get(i)));
                    }

                    mainView.setAdapter(mCardAdapter);


                    Log.e("Success", "getData: onResponse " + response.body());
                }

                @Override
                public void onFailure(Call<List<Rolodata>> call, Throwable t) {
                    Log.e("Failure", "getData: onResponse ");

                }
            });



    }

}





