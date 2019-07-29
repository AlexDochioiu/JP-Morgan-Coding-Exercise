package com.example.jpmorgancodingexercise.general;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

/**
 * Created by Alex Dochioiu on 2019-07-29
 */
public class NetworkUtils {

    private final Context applicationContext;

    /**
     * Constructor
     */
    public NetworkUtils(final Context context) {
        applicationContext = context.getApplicationContext();
    }

    public boolean isNetworkActive() {
        final ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo activeNetwork;

        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        } else {
            activeNetwork = null;
        }

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
