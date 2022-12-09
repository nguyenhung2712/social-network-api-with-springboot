package com.ntth.socialnetwork.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntth.socialnetwork.models.Post;
import com.ntth.socialnetwork.models.User;
import com.ntth.socialnetwork.payload.request.PostRequest;
import com.ntth.socialnetwork.repository.PostRepository;
import com.ntth.socialnetwork.repository.UserRepository;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Post>> getAllPosts() {
		try {
			List<Post> posts = new ArrayList<Post>();
			postRepository.findAll().forEach(posts::add);
	
			if (posts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(posts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
		Optional<Post> post = postRepository.findById(id);
		
		if (post.isPresent()) {
			return new ResponseEntity<>(post.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User currentUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		
		try {
			Post newPost = postRepository
					.save(new Post(postRequest.getContent(), postRequest.getImage(), new java.sql.Date(System.currentTimeMillis()), currentUser));
			return new ResponseEntity<>(newPost, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@PutMapping("/update/{id}")
	public ResponseEntity<Tutorial> updatePost(@PathVariable("id") long id, @RequestBody PostRequest postRequest) {
		Optional<Post> postData = postRepository.findById(id);

		if (tutorialData.isPresent()) {
			Post post = postData.get();
			post.setTitle(tutorial.getTitle());
			post.setDescription(tutorial.getDescription());
			post.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<HttpStatus> deletePostById(@PathVariable("id") Long id) {
		try {
			postRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
