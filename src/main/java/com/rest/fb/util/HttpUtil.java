package com.rest.fb.util;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    public ResponseEntity<HashMap> execute(String url, HttpMethod method, Map<String, String> headerMap, Map<String, Object> requestBody) throws HttpStatusCodeException {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        if (!CollectionUtils.isEmpty(headerMap)) {
            headerMap.forEach((key, value) ->
                    headers.add(key, value)
            );
        }

        HttpEntity<Map<String, Object>> entity;

        if (CollectionUtils.isEmpty(requestBody)) {
            entity = new HttpEntity<>(headers);
        } else {
            entity = new HttpEntity<>(requestBody, headers);
        }

        return restTemplate.exchange(url, method, entity, HashMap.class);
    }
}
