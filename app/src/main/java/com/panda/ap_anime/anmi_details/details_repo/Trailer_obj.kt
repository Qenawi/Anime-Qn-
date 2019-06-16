package com.panda.ap_anime.anmi_details.details_repo

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName

class Trailer_obj {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("iso_3166_1")
    var iso31661: String? = null
    @SerializedName("iso_639_1")
    var iso6391: String? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("site")
    var site: String? = null
    @SerializedName("size")
    var size: Long? = null
    @SerializedName("type")
    var type: String? = null

    override fun equals(other: Any?): Boolean {
        if (other is Trailer_obj && other === this)
            return true

        val user = other as Trailer_obj?

        return user!!.id == this.id && user.name == this.name
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (iso31661?.hashCode() ?: 0)
        result = 31 * result + (iso6391?.hashCode() ?: 0)
        result = 31 * result + (key?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (site?.hashCode() ?: 0)
        result = 31 * result + (size?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }


    companion object {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<Trailer_obj> = object : DiffUtil.ItemCallback<Trailer_obj>() {
            override fun areItemsTheSame(oldItem: Trailer_obj, newItem: Trailer_obj): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Trailer_obj, newItem: Trailer_obj): Boolean {
                return oldItem == newItem
            }
        }
    }
}
