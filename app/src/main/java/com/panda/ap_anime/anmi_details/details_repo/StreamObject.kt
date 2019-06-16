package com.panda.ap_anime.anmi_details.details_repo

import android.support.v7.util.DiffUtil

class StreamObject(dname: String, quality: String, streamname: String) {

    var domainBame: String? = dname
    var quality: String? = quality
    var streamname: String? = streamname


    override fun equals(other: Any?): Boolean {
        if (other is StreamObject && other === this)
            return true

        val user = other as StreamObject?

        return user!!.streamname == this.streamname && user.domainBame == this.domainBame
    }

    override fun hashCode(): Int {
        var result = domainBame?.hashCode() ?: 0
        result = 31 * result + (quality?.hashCode() ?: 0)
        result = 31 * result + (streamname?.hashCode() ?: 0)
        return result
    }


    companion object {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<StreamObject> = object : DiffUtil.ItemCallback<StreamObject>() {
            override fun areItemsTheSame(oldItem: StreamObject, newItem: StreamObject): Boolean {
                return oldItem.streamname == newItem.streamname
            }

            override fun areContentsTheSame(oldItem: StreamObject, newItem: StreamObject): Boolean {
                return oldItem == newItem
            }
        }
    }
}
