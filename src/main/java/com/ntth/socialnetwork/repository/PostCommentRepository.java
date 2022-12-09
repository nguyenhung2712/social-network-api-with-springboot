package com.ntth.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntth.socialnetwork.models.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
	
}
