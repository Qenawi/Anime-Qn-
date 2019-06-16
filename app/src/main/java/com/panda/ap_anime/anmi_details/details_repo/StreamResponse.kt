package com.panda.ap_anime.anmi_details.details_repo

import com.google.gson.annotations.SerializedName
import qenawi.panda.a_predator.network_Handeler.CService_DBase

import java.util.ArrayList

class StreamResponse : CService_DBase() {
    @SerializedName("streamlist")
    var data: ArrayList<StreamObject>? = null

    override fun Is_Data_Good(): Boolean {
        return this.data != null
    }
}
