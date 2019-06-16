package com.panda.ap_anime.descover_fragment.data_repo

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("Result_ids")
    var ResultIds: List<Long>? = null
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("original_language")
    var originalLanguage: String? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("popularity")
    var popularity: Double? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("video")
    var video: Boolean? = null
    @SerializedName("vote_average")
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    var voteCount: Long? = null
    override fun equals(other: Any?): Boolean
    {
        if (other is Result && other === this)
            return true

        val user = other as Result?

        return user!!.id == this.id && user.title == this.title
    }

    override fun hashCode(): Int {
        var result = adult?.hashCode() ?: 0
        result = 31 * result + (backdropPath?.hashCode() ?: 0)
        result = 31 * result + (ResultIds?.hashCode() ?: 0)
        result = 31 * result + id
        result = 31 * result + (originalLanguage?.hashCode() ?: 0)
        result = 31 * result + (originalTitle?.hashCode() ?: 0)
        result = 31 * result + (overview?.hashCode() ?: 0)
        result = 31 * result + (popularity?.hashCode() ?: 0)
        result = 31 * result + (posterPath?.hashCode() ?: 0)
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (video?.hashCode() ?: 0)
        result = 31 * result + (voteAverage?.hashCode() ?: 0)
        result = 31 * result + (voteCount?.hashCode() ?: 0)
        return result
    }


    companion object
    {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<Result> = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }
}
