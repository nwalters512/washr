package com.nwalters.washr.helpers;

import com.google.gson.Gson;

public class GsonProvider {

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
