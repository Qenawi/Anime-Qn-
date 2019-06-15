package com.panda.ap_anime.anmi_details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.panda.ap_anime.R;
public class MyViewHolder_Stream extends RecyclerView.ViewHolder
{
    @BindView(R.id.dname)
    carbon.widget.TextView dname;
    @BindView(R.id.watch)
    carbon.widget.TextView watch;
    @BindView(R.id.quality)
    carbon.widget.TextView quality;
    public MyViewHolder_Stream(View itemView)
        {
        super(itemView);
        ButterKnife.bind(this, itemView);
        }
}
