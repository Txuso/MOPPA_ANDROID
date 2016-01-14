package com.example.txuso.moppa;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.view.View;

/**
 * Created by Txuso on 24/12/15.
 * this extra class helps MOPPA to discover if the device is connected and the wifi is on
 */
public class Util {

    /**
     *
     * @param context the application context in which this method will take place
     * @return boolean saying if the device is connected or not
     */
    public static boolean isConnected(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if (plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB){
            MainActivity.charge.setVisibility(View.INVISIBLE);
        }
        else
            MainActivity.charge.setVisibility(View.VISIBLE);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }

    /**
     *
     * @param context the application context in which this method will take place
     * @return boolean saying if the device wifi is turned on
     */
    public static boolean isWifi(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                    MainActivity.wifi.setVisibility(View.INVISIBLE);
                return activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            }
            else
                return false;

    }

}
