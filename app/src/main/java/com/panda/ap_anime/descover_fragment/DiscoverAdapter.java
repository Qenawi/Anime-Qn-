package com.panda.ap_anime.descover_fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.panda.ap_anime.Constants;
import com.panda.ap_anime.R;
import com.panda.ap_anime.anmi_details.DetailsActivity;
import com.panda.ap_anime.descover_fragment.data_repo.Result;
import timber.log.Timber;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Result> movies;
    private Context c;
    private OnSrelection Call_back;
    private final int MAX_TEXT_SIZE = 45;

    protected DiscoverAdapter(@NonNull List<Result> movies, OnSrelection Call_back) {
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
                .inflate(R.layout.card_view_name_desc_2buttons, parent, false);
        c = parent.getContext();
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.MainTextView.setOnClickListener(view1 -> Call_back.selection(movies.get(viewHolder.getAdapterPosition()).getId()));
        // remove View Click Listner From OnBindView
        Timber.tag("new Instance").v("CC");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Result movie = movies.get(position);
        OnBind(movie, holder);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void replaceData(List<Result> movies) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback((ArrayList<Result>) movies, (ArrayList<Result>) this.movies));
        this.movies.clear();
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(DiscoverAdapter.this);
        ;
    }

    public void updateData(ArrayList<Result> movies) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback((ArrayList<Result>) movies, (ArrayList<Result>) this.movies));
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(this);
    }

    public Result getItem(int position) {
        if (position < 0 || position >= movies.size()) {
            throw new InvalidParameterException("INVALID IDX");
        }
        return movies.get(position);
    }

    public void clearData() {
        movies.clear();
        notifyDataSetChanged();
    }

    private void OnBind(Result data, MyViewHolder viewHolder) {
        viewHolder.MainTextView.setText(data.getTitle());
        String Ovtext = data.getOverview().substring(MAX_TEXT_SIZE, data.getOverview().length());
        if (Ovtext.length() < data.getOverview().length()) {
            Ovtext += " ... ";
        }
        viewHolder.Descreption.setText(Ovtext);
        viewHolder.date.setText(data.getReleaseDate());
        viewHolder.seen.setText(String.valueOf(data.getVoteCount()));
        Glide.with(c).load(Constants.Movies.getImageBaseUrl("w500") + (data.getBackdropPath())).into(viewHolder.back_drp);

    }

    public List<Result> getItems() {
        return movies;
    }


    public interface OnSrelection {
        void selection(int id);
    }
}
