package com.nwalters.washr.models;

public class LocationInfo {

    public int id;
    public String code;
    public String name;
    public double latitude;
    public double longitude;

    @Override
    public String toString() {
        return name;
    }
}
