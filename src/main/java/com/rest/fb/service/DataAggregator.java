package com.rest.fb.service;

import com.rest.fb.domain.Post;
import com.rest.fb.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	HttpUtil httpUtil;

	private String getUrl() {

		String url = restUrl + groupId + "/posts/" + "?fields=message,reactions.limit(0).summary(true)&access_token="
				+ accessToken;
		return url;
	}

	private Map getPosts(Object feed) {
		return (Map) feed;
	}

	private Post getMessageAndLike(Map<String, Object> feed) {

		Post post = new Post();

		post.setMessage(String.valueOf(feed.get("message")));
		Map reactions = (Map) feed.get("reactions");
		Map summary = (Map) reactions.get("summary");
		int totalCount = (int) summary.get("total_count");
		post.setLikesCount(totalCount);
		return post;
	}

	public List<Post> getData() {

		System.out.println(getUrl());
		HashMap<String, Object> body = httpUtil.execute(getUrl(), HttpMethod.GET, null, null).getBody();

		List<Object> data = (List<Object>) body.get("data");

		List<Post> posts = data.stream().map(feed -> getPosts(feed)).filter(feed -> feed.containsKey("message"))
				.map(feed -> {
					return getMessageAndLike(feed);
				}).collect(Collectors.toList());
		return posts;
	}

}
