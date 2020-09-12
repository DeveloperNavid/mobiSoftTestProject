package org.sayar.mobisoftdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.sayar.mobisoftdemo.R;
import org.sayar.mobisoftdemo.activity.mainActivity.MainActivity;

import java.util.Objects;

public class SplashActivity extends BaseActivity {

    private ImageView loadingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @Override
    public void registerWidget() {
        loadingImage = findViewById(R.id.loading_img);
    }

    @Override
    public void setListeners() {
        super.setListeners();
//        display loading gif
        Glide.with(this)
                .load(R.drawable.loading)
                .into(loadingImage);

//        go to main page after delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}