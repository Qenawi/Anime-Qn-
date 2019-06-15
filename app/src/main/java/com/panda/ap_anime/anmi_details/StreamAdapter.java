package com.panda.ap_anime.anmi_details;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.panda.ap_anime.R;
import com.panda.ap_anime.anmi_details.details_repo.StreamObject;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

public class StreamAdapter extends RecyclerView.Adapter<MyViewHolder_Stream> {
    private List<StreamObject> movies;
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

    protected StreamAdapter(@NonNull List<StreamObject> movies, OnSrelection Call_back) {
        this.movies = movies;
        this.Call_back = Call_back;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public MyViewHolder_Stream onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.stream_item_layout, parent, false);
        c = parent.getContext();
        MyViewHolder_Stream viewHolder = new MyViewHolder_Stream(view);
        viewHolder.watch.setOnClickListener(view1 -> Call_back.selection(viewHolder.getAdapterPosition()));
        // remove View Click Listner From OnBindView
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Stream holder, int position) {
        StreamObject movie = movies.get(position);
        OnBind(movie, holder);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void replaceData(List<StreamObject> movies) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback_Stream((ArrayList<StreamObject>) movies, (ArrayList<StreamObject>) this.movies));
        this.movies.clear();
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(StreamAdapter.this);
        ;
    }

    public void updateData(ArrayList<StreamObject> movies) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback_Stream((ArrayList<StreamObject>) movies, (ArrayList<StreamObject>) this.movies));
        this.movies.addAll(movies);
        diffResult.dispatchUpdatesTo(this);
    }

    public StreamObject getItem(int position) {
        if (position < 0 || position >= movies.size()) {
            throw new InvalidParameterException("INVALID IDX");
        }
        return movies.get(position);
    }

    public void clearData() {
        movies.clear();
        notifyDataSetChanged();
    }

    private void OnBind(StreamObject data, MyViewHolder_Stream viewHolder) {
        viewHolder.dname.setText(data.getDomainBame());
        viewHolder.quality.setText(data.getQuality());
        Random random = new Random();
        int next = random.nextInt(colorList.size());
        viewHolder.itemView.setBackgroundColor(c.getResources().getColor(colorList.get(next)));
    }

    public List<StreamObject> getItems() {
        return movies;
    }

    public interface OnSrelection {
        void selection(int id);
    }
}
