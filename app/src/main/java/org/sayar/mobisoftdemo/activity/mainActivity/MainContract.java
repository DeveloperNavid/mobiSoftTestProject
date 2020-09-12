package org.sayar.mobisoftdemo.activity.mainActivity;

import org.sayar.mobisoftdemo.model.MovieEntity;

import java.util.List;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public interface MainContract {

    interface view {
        void onSuccessVideoList(List<MovieEntity> movieEntities);

        void onFailedVideoList(String message);
    }

    interface viewModel {
        void getVideoList(String apiKey, String term);
    }

}
