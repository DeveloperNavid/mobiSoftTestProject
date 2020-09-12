package org.sayar.mobisoftdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.rishabhharit.roundedimageview.RoundedImageView;

import org.sayar.mobisoftdemo.R;
import org.sayar.mobisoftdemo.model.MovieEntity;
import org.sayar.mobisoftdemo.util.AnimationUtils;

import java.util.List;

/**
 * Created by Navid Mahboubi at 9/9/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class MovieAdapter extends PagerAdapter {

    private Context mContext;
    private List<MovieEntity> feedItemList;
    private OnItemSelect onItemSelect;

    public MovieAdapter(List<MovieEntity> feedItemList, Context mContext) {
        this.mContext = mContext;
        this.feedItemList = feedItemList;
    }

    @Override
    public int getCount() {
        return feedItemList.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final MovieEntity videoDto = feedItemList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_movie, container, false);

        RoundedImageView imgSlider = (RoundedImageView) view.findViewById(R.id.img_slider);
        TextView txtDetails = (TextView) view.findViewById(R.id.txt_details);
        new AnimationUtils(txtDetails);

        txtDetails.setText(videoDto.getTitle());
//        Glide.with(mContext).load(videoDto.getPoster()).override(800, 500).into(imgSlider);
        Glide.with(mContext).load(videoDto.getPoster()).fitCenter().into(imgSlider);

        imgSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Slider action
                onItemSelect.callBack(position);
            }
        });

        txtDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelect.callBack(position);
            }
        });

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public interface OnItemSelect {
        void callBack(int position);
    }

    public void setOnItemSelectListener(OnItemSelect result) {
        onItemSelect = result;
    }
}
