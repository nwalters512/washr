package com.nwalters.washr.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.nwalters.washr.models.LocationInfo;

import java.lang.reflect.Type;

public class LocationInfoDeserializer implements JsonDeserializer<LocationInfo> {
    @Override
    public LocationInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = json.getAsJsonObject().get("location_masters");
        return new Gson().fromJson(element, LocationInfo.class);
    }
}
