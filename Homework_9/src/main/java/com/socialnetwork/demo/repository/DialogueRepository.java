package com.socialnetwork.demo.repository;

import com.socialnetwork.demo.model.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogueRepository extends JpaRepository<Dialogue, String> {
}
