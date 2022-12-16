package com.ntth.socialnetwork.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "userprofile")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userprofile_id", nullable = false)
	private Long id;

	@NotBlank
	@Size(max = 40)
	@Column(name = "first_name", nullable = false)
	private String firstName;

  	@NotBlank
  	@Size(max = 20)
  	@Column(name = "last_name", nullable = false)
  	private String lastName;

  	@NotBlank
  	@Column(name = "gender", nullable = false)
  	private boolean gender;
  	
  	@NotBlank
  	@Column(name = "dob", nullable = false)
  	private Date dob;
  	
  	@Size(max = 75)
  	@Column(name = "avatar")
  	private String avatar;
  	
  	@Column(name = "background")
  	private String background;
  	
  	@Column(name = "update_date")
  	private Date updateDate;
  	
  	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	public UserProfile() {
		super();
	}

	public UserProfile(@NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 20) String lastName,
			@NotBlank boolean gender, @NotBlank Date dob, @Size(max = 75) String avatar, String background,
			Date updateDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.avatar = avatar;
		this.background = background;
		this.updateDate = updateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
  	
  	
}
