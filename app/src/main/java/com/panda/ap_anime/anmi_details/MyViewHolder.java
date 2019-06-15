package com.panda.ap_anime.anmi_details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.ImageView;
import com.panda.ap_anime.R;


public class MyViewHolder extends RecyclerView.ViewHolder
{
    @BindView(R.id.main_text)
    carbon.widget.TextView MainTextView;
    public MyViewHolder(View itemView)
    {

        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}
