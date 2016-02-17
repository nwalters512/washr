package com.nwalters.washr;

import com.nwalters.washr.models.Location;
import com.nwalters.washr.models.LocationInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface API {
    @GET("nearbylocation")
    Observable<List<LocationInfo>> getLocations();

    @GET("mydata/{locationCode}")
    Observable<List<Location>> getInfoForLocation(@Path("locationCode") String locationCode);
}
