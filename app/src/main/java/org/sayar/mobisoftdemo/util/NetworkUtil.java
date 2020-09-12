package org.sayar.mobisoftdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Navid Mahboubi at 3/4/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class NetworkUtil {
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(1);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }
        NetworkInfo mobileNetwork = cm.getNetworkInfo(0);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }
}
