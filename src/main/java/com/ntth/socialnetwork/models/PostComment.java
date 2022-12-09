package com.ntth.socialnetwork.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "post_comment")
public class PostComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_comment_id", nullable = false)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "date_comment", nullable = false)
	private Date commentDate;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
	
	@ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

	public PostComment() {
		super();
	}

	public PostComment(Long id, @NotBlank @Size(max = 100) String content, Date commentDate, User user, Post post) {
		super();
		this.id = id;
		this.content = content;
		this.commentDate = commentDate;
		this.user = user;
		this.post = post;
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

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}
