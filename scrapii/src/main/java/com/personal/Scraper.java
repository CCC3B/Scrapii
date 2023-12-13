package com.personal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.json.Json;

import java.io.*;
import java.net.*;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

final class Scraper {
  private static UserAgent usragt;
  public String JsonLink;
  public String pureLink;
  public String topic;
  public int limit;
  static String accessToken;

  public Scraper(String platform, String appID, String username, String version) {
    usragt = new UserAgent(platform, appID, username, version);

  }
  // works
  // Run ouath2 to get acess toke
  // Reach homepage
  // raise exception upon invalid request

  // needs
  // switch credential from system enviorments to input scanner to take advantage
  // of spring security
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public void Ouath2() {

    RestTemplate restTemplate = new RestTemplate();
    String url1 = "https://www.reddit.com/api/v1/access_token";

    HttpHeaders headers = new HttpHeaders();
    Map<String, String> authmap = usragt.authenticator("");
    headers.put("User-Agent", Collections.singletonList(usragt.showUserAgent()));
    String authbody = "grant_type=password&username=" + authmap.get("rdt_user") + "&password="
        + authmap.get("rdt_pass");
    headers.setBasicAuth(authmap.get("rdt_ID"), authmap.get("rdt_secret"));

    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    HttpEntity<?> request = new HttpEntity<>(authbody, headers);

    ResponseEntity<String> response = restTemplate.exchange(url1, HttpMethod.POST, request, String.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      System.out.println("Oauth Post Request Successful");
    } else {
      System.out.println("Oauth Post Request Failed");
      System.out.println(response.getStatusCode());
      System.exit(-1);
    }
    JSONObject responeJson = new JSONObject(response.getBody());
    accessToken = responeJson.getString("access_token");

  }

  public String Home(@NonNull String pureLink, int limit, String topic) {
    this.limit = limit;
    this.pureLink = pureLink;
    this.JsonLink = pureLink + ".json?limit=" + limit;
    System.out.println(JsonLink);
    this.topic = topic;
    RestTemplate homePageAccess = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    headers.put("User-Agent", Collections.singletonList(usragt.showUserAgent()));
    headers.setBearerAuth(accessToken);
    HttpEntity request = new HttpEntity("", headers);
    ResponseEntity<String> response = homePageAccess.exchange(JsonLink, HttpMethod.GET, request, String.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      System.out.println("Home subreddit Get Request Successful");
    } else {
      System.out.println("Home subreddit Get Failed");
      System.out.println(response.getStatusCode());
      System.exit(-1);
    }

    try {
      Traverse(JsonLink, pureLink, limit, topic, response);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return response.getBody();

  }

  public HashSet<String> Traverse(@NonNull String JsonLink, String pureLink, int limit, String Topic,
      ResponseEntity entity) throws IOException {
    JSONObject jsonObj = new JSONObject(entity.getBody().toString());

    List<VideoMetaData> list = new ArrayList<VideoMetaData>();
    for (int i = 1; i < 2; i++) /// start from 1 cause the first listing is not a post
    {
      JSONObject data = jsonObj.getJSONObject("data")
          .getJSONArray("children")
          .getJSONObject(i)
          .getJSONObject("data");

      String audioXML = data.getJSONObject("media").getJSONObject("reddit_video").getString("dash_url");
      String videourl = data.getJSONObject("media").getJSONObject("reddit_video").getString("fallback_url");
      String vidIdentifier = data.getString("name") ;      
      String timeStamp = new SimpleDateFormat("_yyyy_MM_dd").format(Calendar.getInstance().getTime()); 
    Boolean made = new File("scrapii\\src\\main\\resources\\"+vidIdentifier).mkdir();   
    System.out.println(made);
      String vidOutputFile = "scrapii\\src\\main\\resources\\"+vidIdentifier+"\\"+"Video"+timeStamp+".mp4";
      String audioOUtputFile = "scrapii\\src\\main\\resources\\"+vidIdentifier+"\\"+"Audio"+timeStamp+".mp3";

      Document doc = null;
      VideoMetaData vidMeta = new VideoMetaData(data.getString("title"), vidOutputFile,data.getString("link_flair_text") == "NSFW nudity", videourl, null);
      VideoMetaData.downloadUsingNIO(videourl,vidOutputFile);
      vidMeta.setVidPath(vidOutputFile);
      vidMeta.seta
      list.add(vidMeta);
      try {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new URL(audioXML).openStream());
         String audioUrl = data.getString("url")+"/" + doc.getElementsByTagName("BaseURL").item(doc.getElementsByTagName("BaseURL").getLength()-1).getTextContent();
    VideoMetaData.downloadUsingNIO(audioUrl,audioOUtputFile);

      }

      catch (Exception e) {
        System.out.println("audio url is null");
        System.exit(-3);
      }


    System.err.println(list.size());

    return null;

  }
    return null;


}}