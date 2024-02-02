package com.personal;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

import org.bytedeco.ffmpeg.ffmpeg;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import java.nio.channels.Channels;

public class VideoMetaData {
    String vidTitle;
    String vidPath;
    Boolean isNSFW;
    String url;
    String audioPath;
    String viddir;

    public VideoMetaData(String vidTitle, String vidPath, Boolean isNSFW, String url, String audioPath, String viddir) {
        this.vidTitle = vidTitle;
        this.vidPath = vidPath;
        this.isNSFW = isNSFW;
        this.url = url;
        this.audioPath = audioPath;
        this.viddir = viddir;
    }

    public String getAudioPath() {
        return this.audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
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

    public static int downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
        return 0;
    }

    public static String videoAudioMuxer(String audio, String video, String out)
            throws org.bytedeco.javacv.FrameGrabber.Exception,
            org.bytedeco.javacv.FrameRecorder.Exception {
                FFmpegFrameGrabber  grabber1 = new FFmpegFrameGrabber(video);
                FFmpegFrameGrabber  grabber2 = new FFmpegFrameGrabber(audio);
        try {
        
            grabber1.start();
            grabber2.start();
            FrameRecorder recorder = new FFmpegFrameRecorder(out,
                    grabber1.getImageWidth(), grabber1.getImageHeight(),
                    grabber2.getAudioChannels());
            recorder.setFormat("matroska");
            recorder.setVideoQuality(10);
            recorder.setFrameRate(grabber1.getFrameRate());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_MP3); // Set audio format to .mp3
            recorder.start();
            Frame frame1, frame2=null;
            while ((frame1 = grabber1.grabFrame()) != null ||
            ((frame2 = grabber2.grabFrame()) != null)) {
            if (frame1 != null) recorder.record(frame1);
            if (frame2 != null) recorder.record(frame2);
        }
            recorder.stop();
            grabber1.stop();
            grabber2.stop();
        }

         catch (FrameRecorder.Exception | FrameGrabber.Exception e) {
            e.printStackTrace();
        }        return out;

    }

    

    public String getViddir() {
        return this.viddir;
    }

    public void setViddir(String viddir) {
        this.viddir = viddir;
    }

}
