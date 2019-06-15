
package com.panda.ap_anime.anmi_details.details_repo;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import qenawi.panda.a_predator.network_Handeler.CService_DBase;


public class TralerResponse extends CService_DBase {

    @SerializedName("id")
    private Long mId;
    @SerializedName("results")
    private List<Trailer_obj> mResults;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<Trailer_obj> getResults() {
        return mResults;
    }

    public void setResults(List<Trailer_obj> results) {
        mResults = results;
    }

    @Override
    public boolean Is_Data_Good() {
        return this.mResults != null;
    }
}
