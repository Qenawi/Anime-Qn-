package com.panda.ap_anime.anmi_details.details_repo;

public class StreamObject {

    public String getDomainBame() {
        return domainBame;
    }

    public void setDomainBame(String domainBame) {
        this.domainBame = domainBame;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStreamname() {
        return streamname;
    }

    public void setStreamname(String streamname) {
        this.streamname = streamname;
    }


    public StreamObject() {

    }

    public StreamObject(String dname, String quality, String streamname) {
        this.streamname = streamname;
        this.quality = quality;
        this.domainBame = dname;
    }

    private String domainBame;
    private String quality;
    private String streamname;


}
