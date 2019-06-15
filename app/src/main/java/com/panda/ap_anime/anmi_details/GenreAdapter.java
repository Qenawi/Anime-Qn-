package com.panda.ap_anime.anmi_details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.panda.ap_anime.R;
import com.panda.ap_anime.anmi_details.details_repo.Genre;
import timber.log.Timber;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

public class GenreAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Genre> movies;
    private Context c;
    private OnSrelection Call_back;
    private final int MAX_TEXT_SIZE = 45;
    static ArrayList<Integer> colorList = new ArrayList<Integer>() {
        {
            add(R.color.carbon_yellow_a400);
            add(R.color.carbon_amber_400);
            add(R.color.carbon_green_500);
            add(R.color.carbon_blue_400);
            add(R.color.carbon_blue_500);
            add(R.color.carbon_brown_400);
        }
    };

    protected GenreAdapter(@NonNull List<Genre> movies, OnSrelection Call_back) {
        this.movies = movies;
        this.Call_back = Call_back;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.carbon_raw_text_view, parent, false);
        c = parent.getContext();
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.MainTextView.setOnClickListener(view1 -> Call_back.selection(movies.get(viewHolder.getAdapterPosition()).getId()));
        // remove View Click Listner From OnBindView
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Genre movie = movies.get(position);
        OnBind(movie, holder);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void replaceData(List<Genre> movies) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback((ArrayList<Genre>) movies, (ArrayList<Genre>) this.movies));
        this.movies.clear();
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(GenreAdapter.this);
        ;
    }

    public void updateData(ArrayList<Genre> movies) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback((ArrayList<Genre>) movies, (ArrayList<Genre>) this.movies));
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(this);
    }

    public Genre getItem(int position) {
        if (position < 0 || position >= movies.size()) {
            throw new InvalidParameterException("INVALID IDX");
        }
        return movies.get(position);
    }

    public void clearData() {
        movies.clear();
        notifyDataSetChanged();
    }

    private void OnBind(Genre data, MyViewHolder viewHolder) {
        viewHolder.MainTextView.setText(data.getName());
        Random random = new Random();
        int next= random.nextInt(colorList.size());
        viewHolder.MainTextView.setBackgroundColor(c.getResources().getColor(colorList.get(next)));
    }

    public List<Genre> getItems() {
        return movies;
    }

    public interface OnSrelection {
        void selection(int id);
    }
}
