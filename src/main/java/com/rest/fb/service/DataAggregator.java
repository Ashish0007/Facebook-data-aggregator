package com.rest.fb.service;

import com.rest.fb.config.ApplicationConfig;
import com.rest.fb.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;

public class DataAggregator {

    private final String groupId;
    private final String accessToken;
    private final String restUrl;

    public DataAggregator(String groupId, String accessToken, String restUrl) {
        this.groupId = groupId;
        this.accessToken = accessToken;
        this.restUrl = restUrl;
    }

    @Autowired
    ApplicationConfig applicationConfig;

    private String getUrl(){
        return "https://graph.facebook.com/v2.8/342112249150555/posts/?fields=message,reactions.limit(0).summary(true)&access_token=384014745306453|9c28c9e38d8464dfbdccfc3e1d440279";
    }

    private Post getPosts(Object feed){
        return (Post)feed;
    }
    public void getData(){

        System.out.println(getUrl());
        HashMap<String,Object> body = applicationConfig.httpUtil().execute(getUrl(), HttpMethod.GET,null,null).getBody();

        List<Object> data = (List<Object>) body.get("data");

        data.stream()
                .filter(feed -> feed.equals(Post.class))
                .forEach(feed -> System.out.println(getPosts(feed).getMessage()));

        System.out.println();

    }

}
