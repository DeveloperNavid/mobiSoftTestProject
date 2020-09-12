package org.sayar.mobisoftdemo.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.sayar.mobisoftdemo.R;
import org.sayar.mobisoftdemo.Tools;
import org.sayar.mobisoftdemo.activity.movieActivity.MovieContract;
import org.sayar.mobisoftdemo.activity.movieActivity.MovieViewModel;
import org.sayar.mobisoftdemo.adapter.GenreAdapter;
import org.sayar.mobisoftdemo.entity.MovieDataSource;
import org.sayar.mobisoftdemo.model.MovieEntity;
import org.sayar.mobisoftdemo.util.AlertDialogUtils;
import org.sayar.mobisoftdemo.util.AlertUtils;
import org.sayar.mobisoftdemo.util.AnimationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.RealmChangeListener;

public class MovieActivity extends BaseActivity implements MovieContract.view {

    private ImageView backgroundImage;
    private MovieEntity movieEntity;
    private String imdbId;
    private MovieViewModel movieViewModel;
    private AlertDialog loadingDialog;
    private TextView txtTitle, txtMetascore, txtImdbRate, txtImdbVotes, txtYear,
            txtRated, txtDuration, txtPlot, txtLanguage, txtCountry, txtActors, txtDirector;
    private LinearLayout layoutRate;
    private RecyclerView recyclerGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DarkTheme);
        setContentView(R.layout.activity_movie);
        getSupportActionBar().hide();
        load();
    }

    @Override
    public void registerWidget() {
        txtTitle = findViewById(R.id.txt_title);
        txtMetascore = findViewById(R.id.txt_metascore);
        txtImdbRate = findViewById(R.id.txt_rate);
        txtImdbVotes = findViewById(R.id.txt_views);
        txtPlot = findViewById(R.id.txt_plot);
        txtLanguage = findViewById(R.id.txt_language);
        txtCountry = findViewById(R.id.txt_country);
        txtActors = findViewById(R.id.txt_actors);
        txtDirector = findViewById(R.id.txt_director);
        backgroundImage = findViewById(R.id.background);
        layoutRate = findViewById(R.id.layout_rate);
        txtYear = findViewById(R.id.year);
        txtRated = findViewById(R.id.rated);
        txtDuration = findViewById(R.id.duration);
        recyclerGenre = findViewById(R.id.recyclerview_genre);
        imdbId = getIntent().getExtras().getString(Tools.MOVIE_KEY);
        movieViewModel = new MovieViewModel(MovieActivity.this);
        loadingDialog = AlertDialogUtils.getProgressView(MovieActivity.this);
    }

    @Override
    public void setListeners() {
        super.setListeners();
        new AnimationUtils(layoutRate);
    }

    @Override
    public void loadOnline() {
        super.loadOnline();
        loadingDialog.show();
        movieViewModel.getVideo(Tools.API_KEY, imdbId);
    }

    @Override
    public void loadOffline() {
        super.loadOffline();
        Loader loader = new Loader();
        loader.execute();
    }

    private void renderView(MovieEntity movieEntities) {
        this.movieEntity = movieEntities;
        txtTitle.setText(movieEntities.getTitle());
        txtImdbRate.setText(movieEntities.getImdbRating());
        txtImdbVotes.setText(movieEntities.getImdbVotes());
        txtMetascore.setText(movieEntities.getMetascore());
        txtYear.setText(movieEntities.getYear());
        txtRated.setText(movieEntities.getRated());
        txtDuration.setText(movieEntities.getRuntime());
        txtPlot.setText(movieEntities.getPlot());
        txtLanguage.setText(movieEntities.getLanguage());
        txtCountry.setText(movieEntities.getCountry());
        txtActors.setText(movieEntities.getActors());
        txtDirector.setText(movieEntities.getDirector());
        Glide.with(MovieActivity.this).load(movieEntity.getPoster()).into(backgroundImage);
        List<String> genreList = new ArrayList<String>(Arrays.asList(movieEntities.getGenre().split(",")));
        GenreAdapter adapter = new GenreAdapter(genreList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerGenre.setLayoutManager(layoutManager);
        recyclerGenre.setAdapter(adapter);
    }

    @Override
    public void onSuccessVideo(MovieEntity movieEntity) {
        loadingDialog.dismiss();
        this.movieEntity = movieEntity;
        renderView(movieEntity);
    }

    @Override
    public void onFailedVideo(String message) {
        loadingDialog.dismiss();
        AlertUtils.showSnackBar(MovieActivity.this, message);
    }

    private class Loader {

        MovieEntity movieEntity;
        private RealmChangeListener realmListener = new RealmChangeListener() {

            @Override
            public void onChange(Object element) {
                movieEntity.removeAllChangeListeners();
                if (movieEntity.isValid()) {
                    renderView(movieEntity);
                }
            }
        };

        public void execute() {
            movieEntity = MovieDataSource.getInstance().get(imdbId);
            movieEntity.addChangeListener(realmListener);
        }
    }
}