package org.sayar.mobisoftdemo.service;

import android.content.Context;

import org.sayar.mobisoftdemo.Tools;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroClient {

    private static Retrofit getRetrofitInstance(final Context context) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request;
                request = chain.request().newBuilder().build();
                return chain.proceed(request);
            }
        };

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Tools.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getApiService(Context context) {
        return getRetrofitInstance(context).create(ApiServices.class);
    }
}