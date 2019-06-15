package com.panda.ap_anime.descover_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.ImageView;
import com.panda.ap_anime.R;


public class MyViewHolder extends RecyclerView.ViewHolder
{
    @BindView(R.id.name)
    TextView MainTextView;
    @BindView(R.id.descreption)
    TextView Descreption;
    @BindView(R.id.back_drp)
    ImageView back_drp;
    @BindView(R.id.data)
    carbon.widget.TextView date;
    @BindView(R.id.seent_text)
    carbon.widget.TextView seen;
    public MyViewHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
