package com.panda.ap_anime.anmi_details.details_repo

import com.google.gson.annotations.SerializedName
import qenawi.panda.a_predator.network_Handeler.CService_DBase


class TralerResponse : CService_DBase() {

    @SerializedName("id")
    var id: Long? = null
    @SerializedName("results")
    var results: List<Trailer_obj>? = null

    override fun Is_Data_Good(): Boolean {
        return this.results != null
    }
}
