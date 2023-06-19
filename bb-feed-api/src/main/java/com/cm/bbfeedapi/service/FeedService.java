package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.FeedDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface FeedService {
    ResponseEntity<GenericResponse> saveFeed(FeedDto feedDto);
    ResponseEntity<GenericResponse> getFeedById(Long id);
    ResponseEntity<GenericResponse> getAllFeeds();
    ResponseEntity<GenericResponse> deleteFeedById(Long id);
}
