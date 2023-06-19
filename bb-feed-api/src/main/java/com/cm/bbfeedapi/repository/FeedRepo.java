package com.cm.bbfeedapi.repository;

import com.cm.bbfeedapi.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepo extends JpaRepository<Feed,Long> {
    List<Feed> findByFeedContentContaining(String term);
}
