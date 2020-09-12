package org.sayar.mobisoftdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.lang.ref.WeakReference;
import java.security.MessageDigest;

public class BlurLightTransformation extends BitmapTransformation {

    private WeakReference<Context> context;

    public BlurLightTransformation(Context context) {
        super();
        this.context = new WeakReference<>(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap source = null;
        try {
            RenderScript rs = RenderScript.create(context.get());
            source = toTransform.copy(Bitmap.Config.ARGB_8888, true);
            if (source == null) {
                return null;
            }
            Allocation input = Allocation.createFromBitmap(rs, source,
                    Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(12);
            script.setInput(input);
            script.forEach(output);

            // Copy the output to the blurred bitmap
            output.copyTo(source);

            // also darken the image
            Paint paint = new Paint();
//            #0C0C0C
//            ColorFilter filter = new LightingColorFilter(0x90909090, 0x00000000);
//            ColorFilter filter = new LightingColorFilter(0xc9c9c9c9, 0x00000000);
            ColorFilter filter = new LightingColorFilter(0xffffffff, 0x00000000);
//            ColorFilter filter = new LightingColorFilter(0x0C0C0C, 0x00000000);
            paint.setColorFilter(filter);
            Canvas canvas = new Canvas(source);
            canvas.drawBitmap(source, 0, 0, paint);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return source;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update("blur".getBytes());
    }
}
