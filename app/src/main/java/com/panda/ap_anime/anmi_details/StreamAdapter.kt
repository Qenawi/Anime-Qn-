package com.panda.ap_anime.anmi_details

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.panda.ap_anime.R
import com.panda.ap_anime.anmi_details.details_repo.StreamObject
import kotlinx.android.synthetic.main.stream_item_layout.view.*

import java.security.InvalidParameterException
import java.util.ArrayList
import java.util.Random

/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

class StreamAdapter : ListAdapter<StreamObject, MyViewHolder_Stream>(StreamObject.DIFF_CALLBACK) {
    private var c: Context? = null
    private val MAX_TEXT_SIZE = 45


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder_Stream {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.stream_item_layout, parent, false)
        c = parent.context
        val viewHolder = MyViewHolder_Stream(view)
        // remove View Click Listner From OnBindView
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder_Stream, position: Int) {
        val movie = getItem(position)
        OnBind(movie, holder)
    }

    private fun OnBind(data: StreamObject, viewHolder: MyViewHolder_Stream) {
        viewHolder.itemView.dname.text = data.domainBame
        viewHolder.itemView.quality.text = data.quality
    }

    interface OnSrelection {
        fun selection(id: Int)
    }
}
