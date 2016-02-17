package com.nwalters.washr.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nwalters.washr.API;
import com.nwalters.washr.R;
import com.nwalters.washr.WashrAPI;
import com.nwalters.washr.deserializers.LocationInfoDeserializer;
import com.nwalters.washr.helpers.NetworkHelper;
import com.nwalters.washr.models.LocationInfo;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (ListView) findViewById(R.id.list);

        WashrAPI.getLocations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    for (LocationInfo info : result) {
                        Log.d("washr", "info: " + info.toString());
                    }
                    ArrayAdapter<LocationInfo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, result);
                    mList.setAdapter(adapter);
                    mList.setOnItemClickListener((parent, view, position, id) -> WashrAPI.getDataForLocation(result.get(position).code)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(result1 -> {
                                System.out.println(result1);
                            }));
                }, error -> {
                    Log.e("washr", "error: " + error.getMessage());
                    error.printStackTrace();
                });
    }
}
