package com.ntth.socialnetwork.payload.request;

import javax.validation.constraints.Size;

public class PostRequest {
	@Size(max = 150)
	private String content;

	private String image;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
