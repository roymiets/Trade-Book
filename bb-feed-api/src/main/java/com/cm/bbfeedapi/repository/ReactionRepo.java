package com.cm.bbfeedapi.repository;

import com.cm.bbfeedapi.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepo extends JpaRepository<Reaction,Long> {
}
