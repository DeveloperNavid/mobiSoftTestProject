package org.sayar.mobisoftdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sayar.mobisoftdemo.R;

import java.util.List;

/**
 * Created by Navid Mahboubi at 9/9/20.
 * Email: navid.mahboubi96@gmail.com
 * Phone: +989019199835
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private Context mContext;
    private List<String> genreList;


    public GenreAdapter(List<String> genreList, Context mContext) {
        this.mContext = mContext;
        this.genreList = genreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_genre, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtGenre.setText(genreList.get(position));
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGenre=itemView.findViewById(R.id.txt_genre);
        }
    }
}
