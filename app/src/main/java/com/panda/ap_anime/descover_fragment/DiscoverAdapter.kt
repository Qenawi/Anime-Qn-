package com.panda.ap_anime.descover_fragment

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.panda.ap_anime.Constants
import com.panda.ap_anime.R
import com.panda.ap_anime.anmi_details.DetailsActivity
import com.panda.ap_anime.descover_fragment.data_repo.Result
import kotlinx.android.synthetic.main.card_view_name_desc_2buttons.view.*
import timber.log.Timber


import java.security.InvalidParameterException
import java.util.ArrayList


/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

class DiscoverAdapter (val d:OnSrelection) : ListAdapter<Result, MyViewHolder>(Result.DIFF_CALLBACK)
{

    private lateinit var c: Context

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_view_name_desc_2buttons, parent, false)
        c = parent.context
        val viewHolder = MyViewHolder(view)
        viewHolder.itemView.name.setOnClickListener{
            d.selection(getItem(viewHolder.adapterPosition).id)
        }
        return viewHolder
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        OnBind(movie, holder)
    }
    private fun OnBind(data: Result, viewHolder: MyViewHolder) {
        viewHolder.itemView.name.text = data.title

        val Ovtext = data.overview?.let {not_null_overView->
            not_null_overView.substring(0,Math.min(45,not_null_overView.length))
        }
        viewHolder.itemView.descreption.text = Ovtext
        viewHolder.itemView.data.text = data.releaseDate
        viewHolder.itemView.seent_text.text = data.voteCount.toString()
        Glide.with(c).load(Constants.Movies.getImageBaseUrl("w300") + data.backdropPath).into(viewHolder.itemView.back_drp)

    }


    interface OnSrelection {
        fun selection(id: Int)
    }
}
