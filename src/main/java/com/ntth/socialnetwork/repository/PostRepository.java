package com.ntth.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntth.socialnetwork.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
}
