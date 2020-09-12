package org.sayar.mobisoftdemo.activity.movieActivity;

import android.app.Activity;
import android.util.Log;

import org.sayar.mobisoftdemo.activity.MovieActivity;
import org.sayar.mobisoftdemo.entity.MovieDataSource;
import org.sayar.mobisoftdemo.model.MovieEntity;
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

public class MovieViewModel implements MovieContract.viewModel {

    private Activity context;
    private MovieActivity movieView;

    public MovieViewModel(Activity context) {
        this.context = context;
        movieView = (MovieActivity) context;
    }

    @Override
    public void getVideo(String apiKey, String imdbId) {
        ApiServices apiServices = RetroClient.getApiService(context);
        Call<Object> call = apiServices.getVideo(imdbId, apiKey);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.body() == null) {
                    movieView.onFailedVideo(StringUtils.errorMessage(context, response.code()));
                    return;
                }
                ConvertUtils<MovieEntity> convertUtils = new ConvertUtils<>();
                MovieEntity movieEntity = convertUtils.convertJsonToModel(response.body(), MovieEntity.class);
                MovieDataSource.getInstance().save(movieEntity);
                movieView.onSuccessVideo(movieEntity);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                movieView.onFailedVideo(StringUtils.errorMessage(context, 1000));
            }
        });
    }
}
