package com.panda.ap_anime.anmi_details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.panda.ap_anime.R;


public class MyViewHolder_Tralers extends RecyclerView.ViewHolder {
    @BindView(R.id.traler_name)
    carbon.widget.TextView t_name;
    @BindView(R.id.traler_thump)
    YouTubeThumbnailView t_Image;

    public MyViewHolder_Tralers(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind() {

    }

}
