package com.socialnetwork.demo.repository;

import com.socialnetwork.demo.model.FriendLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendLinkRepository extends JpaRepository<FriendLink, String> {

}
