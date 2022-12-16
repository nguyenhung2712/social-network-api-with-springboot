package com.ntth.socialnetwork.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "joinedgroup")
@IdClass(ODPrimaryKey.class)
public class GroupJoinDetails {
	@Id
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = true, updatable = true)
	private Group group;
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "joined_date", nullable = false)
  	private Date joinedDate;
	
	public GroupJoinDetails() {
		super();
	}

	public GroupJoinDetails(Group group, User user, Date joinedDate) {
		super();
		this.group = group;
		this.user = user;
		this.joinedDate = joinedDate;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
  	
}

class ODPrimaryKey implements Serializable {
	
	private Long group;
    private Long user;
    
}