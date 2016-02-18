package com.nwalters.washr.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.nwalters.washr.Constants;
import com.nwalters.washr.R;
import com.nwalters.washr.WashrAPI;
import com.nwalters.washr.adapters.LocationAdapter;
import com.nwalters.washr.models.LocationInfo;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LocationListActivity extends AppCompatActivity {

    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.contains(Constants.PREF_DEFAULT_LOCATION)) {
            launchRoomsActivity(prefs.getString(Constants.PREF_DEFAULT_LOCATION, null), "");
        }

        getSupportActionBar().setTitle(R.string.activity_location_list);

        mList = (ListView) findViewById(R.id.list);

        WashrAPI.getLocations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    mList.setAdapter(new LocationAdapter(this, result));
                    mList.setOnItemClickListener((parent, view, position, id) -> {
                        promptSetDefaultLocation(result.get(position).code, result.get(position).name);
                    });
                }, error -> {
                    error.printStackTrace();
                });
    }

    private void promptSetDefaultLocation(String code, String locationName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set default location");
        builder.setMessage("Would you like to use " + locationName + " as your default location? You can always change it later.");
        builder.setNegativeButton("No", (dialog, which) -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            prefs.edit().remove(Constants.PREF_DEFAULT_LOCATION);
            dialog.cancel();
            launchRoomsActivity(code, locationName);
        });
        builder.setPositiveButton("Yes", (dialog, which) -> {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            prefs.edit().putString(Constants.PREF_DEFAULT_LOCATION, code).commit();
            dialog.cancel();
            launchRoomsActivity(code, locationName);
        });
        builder.create().show();
    }

    private void launchRoomsActivity(String code, String locationName) {
        Intent intent = new Intent(LocationListActivity.this, RoomsActivity.class);
        Bundle b = new Bundle();
        b.putString(RoomsActivity.EXTRA_LOCATION_CODE, code);
        b.putString(RoomsActivity.EXTRA_LOCATION_NAME, locationName);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}
