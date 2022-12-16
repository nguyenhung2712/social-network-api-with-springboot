package com.ntth.socialnetwork.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", nullable = false)
	private Long id;

	@NotBlank
	@Size(max = 150)
	@Column(name = "content", nullable = false)
	private String content;

	@NotBlank
	@Column(name = "image", nullable = false)
	private String image;	
  
	@Column(name = "published_date", nullable = false)
	private Date publishedDate;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL)
  	private Set<PostComment> comment;

	public Post() {
		super();
	}

	public Post(String content, String image, Date publishedDate, User user) {
		super();
		this.content = content;
		this.image = image;
		this.publishedDate = publishedDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date published_date) {
		this.publishedDate = published_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
