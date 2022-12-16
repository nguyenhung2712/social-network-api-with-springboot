package com.ntth.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ntth.socialnetwork.models.GroupJoinDetails;

@Repository
public interface GroupJoinDetailsRepository extends JpaRepository<GroupJoinDetails, Long> {
	@Query(value = "SELECT COUNT(user_id) FROM joinedgroup WHERE group_id = :#{#group_id}"
			, nativeQuery = true)
	public Long getTotalMemberOfGroup(@Param("group_id") Long group_id);
}