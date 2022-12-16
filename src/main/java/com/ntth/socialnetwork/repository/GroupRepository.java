package com.ntth.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ntth.socialnetwork.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	@Query(value = "SELECT j.group_id, created_date, group_about, group_name FROM joinedgroup j JOIN `group` g "
			+ "WHERE user_id = :#{#user_id} AND g.group_id = j.group_id"
			, nativeQuery = true)
	public List<Group> getGroupsUserJoined(@Param("user_id") Long user_id);
}
