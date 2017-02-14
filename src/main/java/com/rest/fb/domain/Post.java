package com.rest.fb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Post {

    private String message;

    private Integer likesCount;
    
    @Id
    private String id;

    public int getLikesCount() {
        return likesCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public void setLikesCount(int totalCount) {
		this.likesCount = totalCount;
	}
}
