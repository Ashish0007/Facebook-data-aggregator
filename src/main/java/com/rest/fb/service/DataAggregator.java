package com.rest.fb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.rest.fb.dao.PostDao;
import com.rest.fb.domain.Post;
import com.rest.fb.util.HttpUtil;

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
	
	@Autowired
	PostDao postDao;

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

	public void getData() {

		HashMap<String, Object> body = httpUtil.execute(getUrl(), HttpMethod.GET, null, null).getBody();

		List<Object> data = (List<Object>) body.get("data");
		Map<String,String> paging = (Map<String, String>) body.get("paging");
		
		List<Post> posts = data.stream().map(feed -> getPosts(feed)).filter(feed -> feed.containsKey("message"))
				.map(feed -> {
					return getMessageAndLike(feed);
				}).collect(Collectors.toList());
		
		postDao.insertData(posts);
		
		while(paging.get("next") != null || paging.get("next").isEmpty()){

			body.clear();
			data.clear();
			paging.clear();
			posts.clear();
			
			body = httpUtil.execute(getUrl(), HttpMethod.GET, null, null).getBody();
			data = (List<Object>) body.get("data");
			paging = (Map<String, String>) body.get("paging");
			
			 posts = data.stream().map(feed -> getPosts(feed)).filter(feed -> feed.containsKey("message"))
					.map(feed -> {
						return getMessageAndLike(feed);
					}).collect(Collectors.toList());
			
			postDao.insertData(posts);
			
		}
	}

}
