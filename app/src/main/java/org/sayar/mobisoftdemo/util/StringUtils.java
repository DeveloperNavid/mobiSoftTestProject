package org.sayar.mobisoftdemo.util;

import android.app.Activity;

import org.sayar.mobisoftdemo.R;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class StringUtils {
    public static String errorMessage(Activity context, int responseCode) {
        String message = "";
        try {
            switch (responseCode) {
                case 500:
                    message = context.getString(R.string.response_code_500);
                    break;
                case 404:
                    message = context.getString(R.string.response_code_404);
                    break;
                case 403:
                case 401:
                    message = context.getString(R.string.response_code_401);
                    break;
                case 400:
                    message = context.getString(R.string.response_code_400);
                    break;
                default:
                    message = context.getString(R.string.on_failed);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

}
