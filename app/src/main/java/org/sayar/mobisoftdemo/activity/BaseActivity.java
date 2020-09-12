package org.sayar.mobisoftdemo.activity;

import android.content.pm.ActivityInfo;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.sayar.mobisoftdemo.util.NetworkUtil;

/**
 * Created by Navid Mahboubi at 3/4/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void registerWidget();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.setContentView(layoutResID);
        registerWidget();
        setListeners();
    }

    public void setListeners() {
    }

    public void loadOnline() {

    }

    public void loadOffline() {

    }

    public void load() {
        if (NetworkUtil.isInternetConnected(this)) {
            loadOnline();
        } else {
            loadOffline();
        }
    }
}
