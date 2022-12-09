package com.ntth.socialnetwork.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostRequest {
	@NotBlank
	@Size(max = 150)
	@Column(name = "content", nullable = false)
	private String content;

	@NotBlank
	@Column(name = "image", nullable = false)
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
