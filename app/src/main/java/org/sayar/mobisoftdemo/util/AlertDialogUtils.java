package org.sayar.mobisoftdemo.util;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

import org.sayar.mobisoftdemo.R;

/**
 * Created by Navid Mahboubi at 9/9/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class AlertDialogUtils {
    public static AlertDialog getProgressView(Activity context) {
        AlertDialog progress;
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder =
                new androidx.appcompat.app.AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        ImageView progressImg = dialogView.findViewById(R.id.loading_img);
        Glide.with(context)
                .load(R.drawable.loading)
                .into(progressImg);
        dialogBuilder.setView(dialogView);
        progress = dialogBuilder.create();
        progress.setCancelable(false);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return progress;
    }


}
