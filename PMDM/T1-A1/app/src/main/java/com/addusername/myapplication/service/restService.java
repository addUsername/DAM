package com.addusername.myapplication.service;


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
        response = restTemplate.getForEntity(url+ "/1", String.class);
        return response.toString();
    }

    public void setUrl(String url) {
        this.url = url;
    }
}