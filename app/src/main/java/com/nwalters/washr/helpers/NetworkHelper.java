package com.nwalters.washr.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;

public class NetworkHelper {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String getFromURL(String urlString) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String response = "", line;

            while ((line = reader.readLine()) != null) {
                response += line;
            }

            return response;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static Observable<JsonElement> getObservableFromUrl(String url) {
        return Observable.create(observer -> {
            if (!observer.isUnsubscribed()) {
                try {
                    JsonElement result = new JsonParser().parse(getFromURL(url));
                    observer.onNext(result);
                } catch (IOException e) {
                    observer.onError(e);
                }
            }
        });
    }
}
