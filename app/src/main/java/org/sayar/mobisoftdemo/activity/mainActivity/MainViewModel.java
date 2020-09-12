package org.sayar.mobisoftdemo.activity.mainActivity;

import android.app.Activity;
import android.util.Log;

import org.sayar.mobisoftdemo.entity.MovieDataSource;
import org.sayar.mobisoftdemo.model.MovieSearchResponse;
import org.sayar.mobisoftdemo.service.ApiServices;
import org.sayar.mobisoftdemo.service.RetroClient;
import org.sayar.mobisoftdemo.util.ConvertUtils;
import org.sayar.mobisoftdemo.util.StringUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class MainViewModel implements MainContract.viewModel {

    private Activity context;
    private MainActivity mainView;

    public MainViewModel(Activity context) {
        this.context = context;
        mainView = (MainActivity) context;
    }

    @Override
    public void getVideoList(String apiKey, String term) {
        ApiServices apiServices = RetroClient.getApiService(context);
        Call<Object> call = apiServices.getVideoList(apiKey, term);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.body() == null) {
                    mainView.onFailedVideoList(StringUtils.errorMessage(context, response.code()));
                    return;
                }
                ConvertUtils<MovieSearchResponse> convertUtils = new ConvertUtils<>();
                MovieSearchResponse movieSearchResponse = convertUtils.convertJsonToModel(response.body(), MovieSearchResponse.class);
                Log.i("VIDEO_LIST", movieSearchResponse.getSearch().size() + "");
                MovieDataSource.getInstance().saveAll(movieSearchResponse.getSearch());
                Log.i("VIDEO_LIST", MovieDataSource.getInstance().getAll().size() + "");
                mainView.onSuccessVideoList(movieSearchResponse.getSearch());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                mainView.onFailedVideoList(StringUtils.errorMessage(context, 1000));
            }
        });
    }
}
