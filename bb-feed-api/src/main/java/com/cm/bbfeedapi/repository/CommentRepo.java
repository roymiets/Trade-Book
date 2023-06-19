package com.cm.bbfeedapi.repository;

import com.cm.bbfeedapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
}
