package org.sayar.mobisoftdemo.activity.movieActivity;

import org.sayar.mobisoftdemo.model.MovieEntity;

import java.util.List;

/**
 * Created by Navid Mahboubi at 9/10/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public interface MovieContract {

    interface view {
        void onSuccessVideo(MovieEntity movieEntities);

        void onFailedVideo(String message);
    }

    interface viewModel {
        void getVideo(String apiKey, String imdbId);
    }

}
