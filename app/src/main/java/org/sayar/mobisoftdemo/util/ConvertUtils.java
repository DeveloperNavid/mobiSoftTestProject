package org.sayar.mobisoftdemo.util;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class ConvertUtils<T> {

    public static int convertDimenToPixel(Context context, int dimenId) {
        int valueInPixels = (int) context.getResources().getDimension(dimenId);
        return valueInPixels;
    }

    public List<T> convertJsonListToModel(List<Object> objectList, Class<T> convertedModel) {
        List<T> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            if (objectList != null) {
                for (Object object : objectList)
                    list.add(gson.fromJson(gson.toJson(object), convertedModel));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public T convertJsonToModel(Object object, Class<T> convertedModel) {
        T item = null;
        Gson gson = new Gson();
        if (object != null)
            item = gson.fromJson(gson.toJson(object), convertedModel);
        return item;
    }

    public T convertStringToModel(String data, Class<T> convertedModel) {
        T item = null;
        Gson gson = new Gson();
        if (data != null)
            item = gson.fromJson(gson.toJson(data), convertedModel);
        return item;
    }
}
