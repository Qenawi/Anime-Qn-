package com.panda.ap_anime.anmi_details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.panda.ap_anime.MainActivity;
import com.panda.ap_anime.R;
import com.panda.ap_anime.anmi_details.details_repo.Trailer_obj;
import com.panda.ap_anime.descover_fragment.DescoverFragment;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

public class TralersAdapter extends RecyclerView.Adapter<MyViewHolder_Tralers> {
    private List<Trailer_obj> movies;
    private Context c;
    private OnSrelection Call_back;
    private final int MAX_TEXT_SIZE = 45;
    static ArrayList<Integer> colorList = new ArrayList<Integer>() {
        {
            add(R.color.carbon_blue_400);
            add(R.color.carbon_green_500);
            add(R.color.carbon_amber_500);
            add(R.color.carbon_amber_800);
            add(R.color.carbon_teal_900);
            add(R.color.carbon_teal_300);
        }
    };

    protected TralersAdapter(@NonNull List<Trailer_obj> movies, OnSrelection Call_back) {
        this.movies = movies;
        this.Call_back = Call_back;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
   // 73155133
    @NonNull
    @Override
    public MyViewHolder_Tralers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.vedio_layout, parent, false);
        c = parent.getContext();
        MyViewHolder_Tralers viewHolder = new MyViewHolder_Tralers(view);
        viewHolder.t_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Call_back.selection(viewHolder.getAdapterPosition());
            }
        });
        // remove View Click Listner From OnBindView
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Tralers holder, int position) {
        Trailer_obj movie = movies.get(position);
        OnBind(movie, holder);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void replaceData(List<Trailer_obj> movies) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback_Tralers((ArrayList<Trailer_obj>) movies, (ArrayList<Trailer_obj>) this.movies));
        this.movies.clear();
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(TralersAdapter.this);
        ;
    }

    public void updateData(ArrayList<Trailer_obj> movies) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback_Tralers((ArrayList<Trailer_obj>) movies, (ArrayList<Trailer_obj>) this.movies));
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(this);
    }

    public Trailer_obj getItem(int position) {
        if (position < 0 || position >= movies.size()) {
            throw new InvalidParameterException("INVALID IDX");
        }
        return movies.get(position);
    }

    public void clearData() {
        movies.clear();
        notifyDataSetChanged();
    }

    private void OnBind(Trailer_obj data, MyViewHolder_Tralers viewHolder) {
        //viewHolder un used instance needed to be re populated ....
        viewHolder.t_name.setText(data.getName());
        Random random = new Random();
        int next = random.nextInt(colorList.size());
        viewHolder.itemView.setBackgroundColor(c.getResources().getColor(colorList.get(next)));
        viewHolder.t_Image.initialize(DescoverFragment.ApiKeyYoutube, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(data.getKey());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        youTubeThumbnailLoader.release();
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    public List<Trailer_obj> getItems() {
        return movies;
    }

    public interface OnSrelection {
        void selection(int id);
    }
}
