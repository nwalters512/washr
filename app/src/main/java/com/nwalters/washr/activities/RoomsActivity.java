package com.nwalters.washr.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.nwalters.washr.R;
import com.nwalters.washr.WashrAPI;
import com.nwalters.washr.adapters.RoomAdapter;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RoomsActivity extends AppCompatActivity {

    public static final String EXTRA_LOCATION_CODE = "location_code";
    public static final String EXTRA_LOCATION_NAME = "location_name";

    private String mLocationCode;
    private String mLocationName;

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        mList = (ListView) findViewById(R.id.list);

        String mLocationCode = getIntent().getExtras().getString(EXTRA_LOCATION_CODE, null);
        String mLocationName = getIntent().getExtras().getString(EXTRA_LOCATION_NAME, null);

        getSupportActionBar().setTitle(mLocationName);

        WashrAPI.getDataForLocation(mLocationCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    getSupportActionBar().setTitle(result.name);
                    mList.setAdapter(new RoomAdapter(this, result.rooms));
                }, error -> {
                    error.printStackTrace();
                });
    }
}
