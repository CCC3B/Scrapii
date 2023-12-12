package com.personal;

import javax.swing.text.Document;

public class VideoMetaData {
    String vidTitle;
    org.w3c.dom.Document audioxml;
    String vidPath;
    Boolean isNSFW;
    String url;
    public VideoMetaData( String vidTitle, String vidPath, Boolean isNSFW, String url, org.w3c.dom.Document doc)
        {
        this.vidTitle=vidTitle;
        this.vidPath=vidPath;
        this.isNSFW=isNSFW;
        this.url = url;
        this.audioxml = doc;
    }

    public org.w3c.dom.Document getAudioxml() {
        return this.audioxml;
    }

    public void setAudioxml(org.w3c.dom.Document audioxml) {
        this.audioxml = audioxml;
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
