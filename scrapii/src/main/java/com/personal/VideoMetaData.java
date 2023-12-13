package com.personal;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
public class VideoMetaData {
    String vidTitle;
    String vidPath;
    Boolean isNSFW;
    String url;
    String audioURL;
    public VideoMetaData( String vidTitle, String vidPath, Boolean isNSFW, String url, String audioURL)
        {
        this.vidTitle=vidTitle;
        this.vidPath=vidPath;
        this.isNSFW=isNSFW;
        this.url = url;
        this.audioURL = audioURL;
    }

    public String getAudioURL() {
        return this.audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
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

    public  static int downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        return 0;
    }
}
    

