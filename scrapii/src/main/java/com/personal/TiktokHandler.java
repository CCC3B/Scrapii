package com.personal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

public class TiktokHandler {
    private static UserAgent usragt;
    private static String accessToken;
    private final String PCKA="";
    List<VideoMetaData> VideoList;    
    boolean isDuplicated ;
    public TiktokHandler(String platform, String appID) {
        usragt = new UserAgent(platform, appID, "", "");
    
      }

      @ExceptionHandler(MethodArgumentNotValidException.class)
  public void Ouath2() {

    RestTemplate restTemplate = new RestTemplate();
    String url1 = "https://open.tiktokapis.com/v2/oauth/token/";

    HttpHeaders headers = new HttpHeaders();
    Map<String, String> authmap = usragt.authenticator("");

    headers.add("User-Agent", "Windows enter478");
   /*  String authbody = "grant_type=password&username=" + authmap.get("rdt_user") + "&password="
        + authmap.get("rdt_pass");*/
    headers.setBasicAuth(authmap.get("tik_client"), authmap.get("tik_secret"));

    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    HttpEntity<?> request = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(url1, HttpMethod.POST, request, String.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      System.out.println("Oauth Post Request Successful");
    } else {
      System.out.println("Oauth Post Request Failed");
      System.out.println(response.getStatusCode());
      System.exit(-1);
    }
    JSONObject responeJson = new JSONObject(response.getBody());
    accessToken = responeJson.toString();
    System.out.println(accessToken);

  }
  String generateRandomString(int length) {
    String  result = "";
    String  characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-.~_:()";
    int charactersLength = characters.length();
    for (var i = 0; i < length; i++) {
      result += characters.charAt((int)Math.floor(Math.random() * charactersLength));
    }
    return result;
  }

    
}