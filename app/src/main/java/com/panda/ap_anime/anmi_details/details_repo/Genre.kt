package com.panda.ap_anime.anmi_details.details_repo

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Genre {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

    override fun equals(other: Any?): Boolean
    {
        if (other is Genre && other === this)
            return true

        val user = other as Genre?

        return user!!.id == this.id && user.name == this.name
    }

    override fun hashCode(): Int
    {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }


    companion object
    {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<Genre> = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem == newItem
            }
        }
    }

}
