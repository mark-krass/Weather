package com.example.gambm.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lvSimple)
    protected ListView lvsimple;

    @BindView(R.id.etSearch)
    protected EditText etsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.btnSearch)
    protected void onClick() {
        {
            WeatherService weatherService = WeatherService.retrofit.create(WeatherService.class);
            String q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"moscow\")&format=json&env";
            final Call<ArrayList<Info>> call = weatherService.repoContributors(q);
            call.enqueue(new Callback<ArrayList<Info>>() {
                @Override
                public void onResponse(Call<ArrayList<Info>> call, Response<ArrayList<Info>> response) {
                    lvsimple.setAdapter(new BoxAdapter(MainActivity.this, response.body()));
                }

                @Override
                public void onFailure(Call<ArrayList<Info>> call, Throwable t) {
                    Log.d("mylogs", String.valueOf(t));
                }
            });
        }
    }
}
