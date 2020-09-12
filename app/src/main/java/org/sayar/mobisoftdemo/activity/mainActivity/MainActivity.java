package org.sayar.mobisoftdemo.activity.mainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.av.smoothviewpager.Smoolider.SmoothViewpager;
import com.av.smoothviewpager.utils.Txt_Factory;
import com.bumptech.glide.Glide;

import org.sayar.mobisoftdemo.R;
import org.sayar.mobisoftdemo.Tools;
import org.sayar.mobisoftdemo.activity.BaseActivity;
import org.sayar.mobisoftdemo.activity.MovieActivity;
import org.sayar.mobisoftdemo.adapter.MovieAdapter;
import org.sayar.mobisoftdemo.entity.MovieDataSource;
import org.sayar.mobisoftdemo.model.MovieEntity;
import org.sayar.mobisoftdemo.util.AlertUtils;
import org.sayar.mobisoftdemo.util.AnimationUtils;
import org.sayar.mobisoftdemo.util.AlertDialogUtils;
import org.sayar.mobisoftdemo.util.RealmResultsUtil;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.av.smoothviewpager.utils.Smoolider_Utils.autoplay_viewpager;
import static com.av.smoothviewpager.utils.Smoolider_Utils.stop_autoplay_ViewPager;

public class MainActivity extends BaseActivity implements MainContract.view {

    private SmoothViewpager viewPager;
    private TextSwitcher txtSwitcherPosition;
    private TextView txtTitle;
    private TextView txtSubtitle;
    private boolean isAutoPlay = false;
    private int currentPosition;
    private LottieAnimationView animationView;
    private List<MovieEntity> movieEntities;
    private AlertDialog loadingDialog;
    private MainViewModel mainViewModel;
    private ImageView backgroundImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Loader loader = new Loader();
        loader.execute();
    }

    @Override
    public void registerWidget() {
        backgroundImg = findViewById(R.id.background);
        viewPager = (SmoothViewpager) findViewById(R.id.smoolider);
        txtSwitcherPosition = (TextSwitcher) findViewById(R.id.txt_swithcher_position);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtSubtitle = (TextView) findViewById(R.id.txt_subtitle);
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.autoplay_animation);
        mainViewModel = new MainViewModel(this);
        txtSwitcherPosition.setFactory(new Txt_Factory(R.style.CustomTextView, true, getApplicationContext()));
        loadingDialog = AlertDialogUtils.getProgressView(MainActivity.this);

//        animate when clicked
        new AnimationUtils(txtTitle);
        new AnimationUtils(txtSubtitle);
    }

    @Override
    public void setListeners() {
        super.setListeners();
        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageAutoPlay();
                AlertUtils.showSnackBar(MainActivity.this, " ▶ Autoplay :  " + isAutoPlay);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {

            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(final int position) {
                manageWidgetsOnSwipe(position);
            }
        });
    }

    @Override
    public void onSuccessVideoList(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
        loadingDialog.dismiss();
        adapterList();
        manageWidgetsOnSwipe(0);
    }

    @Override
    public void onFailedVideoList(String message) {
        loadingDialog.dismiss();
        AlertUtils.showSnackBar(MainActivity.this, message);
    }

    @Override
    public void loadOnline() {
        super.loadOnline();
        if (movieEntities.size() == 0) {
            loadingDialog.show();
            mainViewModel.getVideoList(Tools.API_KEY, Tools.SEARCH_TERM);
        } else {
            adapterList();
            manageWidgetsOnSwipe(0);
        }
    }

    @Override
    public void loadOffline() {
        super.loadOffline();
        if (movieEntities.size() > 0) {
            adapterList();
            manageWidgetsOnSwipe(0);
        } else {
            AlertUtils.showSnackBar(MainActivity.this, getString(R.string.no_network));
        }
    }

    @SuppressLint("SetTextI18n")
    private void manageWidgetsOnSwipe(int pos) {
        new AnimationUtils(txtTitle);
        new AnimationUtils(txtSubtitle);
        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};
        boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }
        int current = pos % movieEntities.size();

//        change background
        Glide.with(this).load(movieEntities.get(current).getPoster()).into(backgroundImg);

//        change page number
        txtSwitcherPosition.setInAnimation(MainActivity.this, animH[0]);
        txtSwitcherPosition.setOutAnimation(MainActivity.this, animH[1]);
        txtSwitcherPosition.setText((current + 1) + "/" + movieEntities.size());

//        change title
        txtTitle.setVisibility(View.INVISIBLE);
        txtTitle.startAnimation(android.view.animation.AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(movieEntities.get(current).getTitle());

//        change subTitle
        txtSubtitle.setVisibility(View.INVISIBLE);
        txtSubtitle.startAnimation(android.view.animation.AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        txtSubtitle.setVisibility(View.VISIBLE);
        txtSubtitle.setText(movieEntities.get(current).getType() + "(" + movieEntities.get(current).getYear() + ")");
        currentPosition = pos;
    }

    private void manageAutoPlay() {
        animationView.playAnimation();
        if (isAutoPlay) {
            isAutoPlay = false;
            stop_autoplay_ViewPager();
        } else {
            isAutoPlay = true;
            autoplay_viewpager(viewPager, movieEntities.size());
        }
    }

    private void adapterList() {
        MovieAdapter adapter = new MovieAdapter(movieEntities, MainActivity.this);
        adapter.setOnItemSelectListener(new MovieAdapter.OnItemSelect() {
            @Override
            public void callBack(int position) {
                Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                intent.putExtra(Tools.MOVIE_KEY, (Serializable) movieEntities.get(position).getImdbID());
                startActivity(intent);
            }
        });
        viewPager.setAdapter(adapter);
    }

    public void renderView(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
        load();
    }

    private class Loader {

        private RealmResults<MovieEntity> movieEntities;
        private RealmChangeListener realmListener = new RealmChangeListener() {

            @Override
            public void onChange(Object element) {
                movieEntities.removeAllChangeListeners();
                renderView(RealmResultsUtil.convert(movieEntities));
            }
        };

        public void execute() {
            movieEntities = MovieDataSource.getInstance().getAll();
            movieEntities.addChangeListener(realmListener);
        }
    }
}