package com.personal;

import java.io.IOException;
import java.text.StringCharacterIterator;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.json.Json;

import java.io.*;
import java.net.*;

final class Scraper {
  private static UserAgent usragt;
  private String link;
  private static int limit;

  public Scraper(String platform, String appID, String username, String version, String link) {
    usragt = new UserAgent(platform, appID, username, version);
    this.link = link;

  }
  //works
  //Run ouath2 to get acess toke
  //raise exception upon invalid request
  
  //needs
  //switch credential from system enviorments to input scanner to take advantage of spring security
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> Ouath2(String currlink, String pagefilter) {


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
    System.out.println(headers.toString());

    System.out.println(request.getBody());

    ResponseEntity<?> response = restTemplate.exchange(url1,HttpMethod.POST, request, String.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      System.out.println("Request Successful");
      System.out.println(response.getBody());
    } else {
      System.out.println("Request Failed");
      System.out.println(response.getStatusCode());
    }
    return response;

  }

  public static Json traverse(String currLink, String prevLink) {
    Json jsn = new Json();
    return jsn;
    
  }

}