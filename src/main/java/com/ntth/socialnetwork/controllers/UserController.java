package com.ntth.socialnetwork.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntth.socialnetwork.models.Group;
import com.ntth.socialnetwork.models.GroupJoinDetails;
import com.ntth.socialnetwork.models.User;
import com.ntth.socialnetwork.models.UserProfile;
import com.ntth.socialnetwork.payload.request.JoinGroupRequest;
import com.ntth.socialnetwork.payload.request.UserRequest;
import com.ntth.socialnetwork.repository.GroupJoinDetailsRepository;
import com.ntth.socialnetwork.repository.GroupRepository;
import com.ntth.socialnetwork.repository.UserProfileRepository;
import com.ntth.socialnetwork.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	GroupRepository groupRepo;
	@Autowired
	UserProfileRepository userprofileRepo;
	@Autowired
	GroupJoinDetailsRepository joindetailsRepo;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUsesList() {
		try {
			List<User> allUsers = userRepo.findAll();
			return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/get-by-user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> getUserProfileByUserAccount(@Valid @RequestBody UserRequest userReq) {
		try {
			User user = userRepo.findByUsername(userReq.getUsername())
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userReq.getUsername()));
			Optional<UserProfile> profile = userprofileRepo.findByUser(user);
			if (profile.isPresent()) {
				return new ResponseEntity<>(profile.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/join-group")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<GroupJoinDetails> joinGroup(@Valid @RequestBody JoinGroupRequest data) {
		try {
			User user = userRepo.findById(data.getUserId()).get();
			Group group = groupRepo.findById(data.getGroupId()).get();
			
			GroupJoinDetails joinDetails = joindetailsRepo.save(new GroupJoinDetails(group, user, 
					new java.sql.Date(System.currentTimeMillis())));
			return new ResponseEntity<GroupJoinDetails>(joinDetails, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
