package com.nwalters.washr;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.nwalters.washr.helpers.GsonProvider;
import com.nwalters.washr.helpers.NetworkHelper;
import com.nwalters.washr.models.Location;
import com.nwalters.washr.models.LocationInfo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class WashrAPI {

    public static Observable<List<LocationInfo>> getLocations() {
        return NetworkHelper.getObservableFromUrl("http://23.23.147.128/homes/nearbylocation")
                .map(item -> {
                    JsonArray locations = item.getAsJsonArray();
                    List<LocationInfo> locationInfo = new ArrayList<>();

                    for (int i = 0; i < locations.size(); i++) {
                        JsonElement location = locations.get(i).getAsJsonObject().get("location_masters");
                        LocationInfo info = GsonProvider.getGson().fromJson(location, LocationInfo.class);
                        locationInfo.add(info);
                    }

                    return locationInfo;
                });
    }

    public static Observable<Location> getDataForLocation(String locationCode) {
        return NetworkHelper.getObservableFromUrl("http://23.23.147.128/homes/mydata/urba7723")
                .map(item -> {
                    return GsonProvider.getGson().fromJson(item.getAsJsonObject().get("location"), Location.class);
                });
    }
}
