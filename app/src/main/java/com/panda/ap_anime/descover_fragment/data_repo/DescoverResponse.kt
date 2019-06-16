package com.panda.ap_anime.descover_fragment.data_repo

import com.google.gson.annotations.SerializedName
import qenawi.panda.a_predator.network_Handeler.CService_DBase

class DescoverResponse : CService_DBase() {
    @SerializedName("page")
    var page: Long? = null
    @SerializedName("results")
    var results: List<Result>? = null
    @SerializedName("total_pages")
    var totalPages: Long? = null
    @SerializedName("total_results")
    var totalResults: Long? = null

    override fun Is_Data_Good(): Boolean {
        return results != null
    }
}
