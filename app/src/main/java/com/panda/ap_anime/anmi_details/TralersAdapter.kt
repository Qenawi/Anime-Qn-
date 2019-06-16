package com.panda.ap_anime.anmi_details

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.panda.ap_anime.MainActivity
import com.panda.ap_anime.R
import com.panda.ap_anime.anmi_details.details_repo.Trailer_obj
import com.panda.ap_anime.descover_fragment.DescoverFragment
import kotlinx.android.synthetic.main.vedio_layout.view.*

import java.security.InvalidParameterException
import java.util.ArrayList
import java.util.Random


/**
 * Created by Ahmed Kamal on 21-11-2017.
 */

class TralersAdapter : ListAdapter<Trailer_obj, MyViewHolder_Tralers>(Trailer_obj.DIFF_CALLBACK)
{
    private var c: Context? = null
    private val MAX_TEXT_SIZE = 45


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder_Tralers {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.vedio_layout, parent, false)
        c = parent.context
        return MyViewHolder_Tralers(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder_Tralers, position: Int) {
        val movie = getItem(position)
        OnBind(movie, holder)
    }
    private fun OnBind(data: Trailer_obj, viewHolder: MyViewHolder_Tralers)
    {
        //viewHolder un used instance needed to be re populated ....
        viewHolder.itemView.traler_name.text = data.name
        viewHolder.itemView.traler_thump.initialize(
            DescoverFragment.ApiKeyYoutube,
            object : YouTubeThumbnailView.OnInitializedListener
            {
                override fun onInitializationSuccess(
                    youTubeThumbnailView: YouTubeThumbnailView,
                    youTubeThumbnailLoader: YouTubeThumbnailLoader
                ) {
                    youTubeThumbnailLoader.setVideo(data.key)
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(object :
                        YouTubeThumbnailLoader.OnThumbnailLoadedListener {
                        override fun onThumbnailLoaded(youTubeThumbnailView: YouTubeThumbnailView, s: String) {
                            youTubeThumbnailLoader.release()
                        }

                        override fun onThumbnailError(
                            youTubeThumbnailView: YouTubeThumbnailView,
                            errorReason: YouTubeThumbnailLoader.ErrorReason
                        ) {
                            youTubeThumbnailLoader.release()
                        }
                    })
                }

                override fun onInitializationFailure(
                    youTubeThumbnailView: YouTubeThumbnailView,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {

                }
            })
    }

    interface OnSrelection {
        fun selection(id: Int)
    }
}
