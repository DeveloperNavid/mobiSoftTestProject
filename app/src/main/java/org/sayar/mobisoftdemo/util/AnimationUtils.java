package org.sayar.mobisoftdemo.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by Navid Mahboubi at 9/9/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */


public class AnimationUtils {
    private WeakReference<View> view;

    public AnimationUtils(View view) {
        this.view = new WeakReference<>(view);
        if (this.view.get() != null) {
            if (!this.view.get().hasOnClickListeners()) {
                this.view.get().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        }
        explode();
    }

    private void explode() {
        view.get().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean touchStatus = false;
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        animate(view, 0);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        view.animate().cancel();
                        animate(view, 1);
                        touchStatus = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        view.animate().cancel();
                        animate(view, 2);
                        animate(view, 3);
                        break;
                }
                return touchStatus;
            }
        });

    }

    private void animate(View view, Integer sequence) {
        int delay = 0;
        int duration = 0;
        float zoom_x = 0f;
        float zoom_y = 0f;
        switch (sequence) {
            case 0:
            case 2:
                zoom_x = 0.9f;
                zoom_y = 0.9f;
                duration = 100;
                delay = 0;
                break;
            case 1:
                zoom_x = 1f;
                zoom_y = 1f;
                duration = 100;
                delay = 0;
                break;
            case 3:
                zoom_x = 1f;
                zoom_y = 1f;
                duration = 100;
                delay = 101;
                break;

        }
        ObjectAnimator scale_x = ObjectAnimator.ofFloat(view, "scaleX", zoom_x);
        ObjectAnimator scale_y = ObjectAnimator.ofFloat(view, "scaleY", zoom_y);
        AnimatorSet animatorSet = new AnimatorSet();
        scale_x.setDuration(Long.parseLong(duration + ""));
        scale_y.setDuration(Long.parseLong(duration + ""));
        animatorSet.playTogether(scale_x, scale_y);
        animatorSet.setStartDelay(Long.parseLong(delay + ""));
        animatorSet.start();
    }
}