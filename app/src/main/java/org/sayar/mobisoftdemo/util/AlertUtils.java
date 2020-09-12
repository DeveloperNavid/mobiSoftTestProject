package org.sayar.mobisoftdemo.util;

import android.app.Activity;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import org.sayar.mobisoftdemo.R;


/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class AlertUtils {
    public static void showSnackBar(Activity context, String message) {
        Snackbar snackBar = Snackbar.make(context.findViewById(android.R.id.content), message, 2000);
        View view = snackBar.getView();
        TextView textView = view.findViewById(R.id.snackbar_text);
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setMaxLines(7);
        textView.setTextSize(ConvertUtils.convertDimenToPixel(context, R.dimen.normal_text));
        textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        snackBar.show();
    }
}
