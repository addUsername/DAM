package com.addusername.myapplication.service;


import androidx.annotation.NonNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class restService {

    private String url;
    private RestTemplate restTemplate;
    private ResponseEntity<String> response;


    public restService(){
        restTemplate = new RestTemplate();
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    }
    public String getResponse(){
        //Check how to inject headers and body also
        response = restTemplate.getForEntity (url+ "/1", String.class);
        return response.toString();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        //Here we should return the atributes as String json to save it ez
        return super.toString();
    }
}