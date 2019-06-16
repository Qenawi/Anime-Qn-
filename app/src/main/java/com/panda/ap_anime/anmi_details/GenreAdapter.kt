package com.panda.ap_anime.anmi_details

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.panda.ap_anime.R
import com.panda.ap_anime.anmi_details.details_repo.Genre
import kotlinx.android.synthetic.main.carbon_raw_text_view.view.*
/**
 * Created by Ahmed Kamal on 21-11-2017.
 */
class GenreAdapter internal constructor() :
    ListAdapter<Genre, MyViewHolder>(Genre.DIFF_CALLBACK) {
    private var c: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.carbon_raw_text_view, parent, false)
        c = parent.context
        return MyViewHolder(view)}
    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val movie = this.getItem(position)
        OnBind(movie, holder)
    }

    private fun OnBind(data: Genre, viewHolder: MyViewHolder) {
        viewHolder.itemView.main_text.text = data.name
    }
}