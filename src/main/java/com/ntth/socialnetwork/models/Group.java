package com.ntth.socialnetwork.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "`group`")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id", nullable = false)
	private Long id;

	@NotBlank
	@Size(max = 20)
	@Column(name = "group_name", nullable = false)
	private String groupName;

  	@NotBlank
  	@Size(max = 50)
  	@Column(name = "group_about", nullable = false)
  	private String groupAbout;
  
  	@Column(name = "created_date", nullable = false)
  	private Date createdDate;
  	
  	@JsonIgnore
  	@OneToMany(mappedBy="group", cascade = CascadeType.ALL)
  	private Set<GroupJoinDetails> gpDetailsList;

	public Group() {
		super();
	}

	public Group(@NotBlank @Size(max = 20) String groupName, @NotBlank @Size(max = 50) String groupAbout,
			Date createdDate) {
		super();
		this.groupName = groupName;
		this.groupAbout = groupAbout;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupAbout() {
		return groupAbout;
	}

	public void setGroupAbout(String groupAbout) {
		this.groupAbout = groupAbout;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<GroupJoinDetails> getGpDetailsList() {
		return gpDetailsList;
	}

	public void setGpDetailsList(Set<GroupJoinDetails> gpDetailsList) {
		this.gpDetailsList = gpDetailsList;
	}
  	
  	
}
