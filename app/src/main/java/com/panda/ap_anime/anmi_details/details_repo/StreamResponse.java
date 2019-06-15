package com.panda.ap_anime.anmi_details.details_repo;

import com.google.gson.annotations.SerializedName;
import qenawi.panda.a_predator.network_Handeler.CService_DBase;

import java.util.ArrayList;

public class StreamResponse extends CService_DBase
{
    public ArrayList<StreamObject> getData() {
        return data;
    }

    public void setData(ArrayList<StreamObject> data) {
        this.data = data;
    }
    @SerializedName("streamlist")
    private ArrayList<StreamObject> data;

    @Override
    public boolean Is_Data_Good() {
        return this.data != null;
    }
}
