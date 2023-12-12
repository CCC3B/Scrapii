package com.personal;


public class VideoMetaData {
    String vidTitle;
    String vidPath;
    Boolean isNSFW;
    String url;
    public VideoMetaData(String vidTitle, String vidPath, Boolean isNSFW, String url)
        {
        this.vidTitle=vidTitle;
        this.vidPath=vidPath;
        this.isNSFW=isNSFW;
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public String getVidTitle() {
        return this.vidTitle;
    }

    public void setVidTitle(String vidTitle) {
        this.vidTitle = vidTitle;
    }

    public String getVidPath() {
        return this.vidPath;
    }

    public void setVidPath(String vidPath) {
        this.vidPath = vidPath;
    }

    public Boolean isIsNSFW() {
        return this.isNSFW;
    }

    public Boolean getIsNSFW() {
        return this.isNSFW;
    }

    public void setIsNSFW(Boolean isNSFW) {
        this.isNSFW = isNSFW;
    }

    
    
}
