package com.cm.bbfeedapi.controller;

import com.cm.bbfeedapi.dto.FeedDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.service.FeedServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/feed")
@RestController
public class FeedController {
    private final FeedServiceImpl feedService;
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Heart beating");
    }
    @PostMapping("/create")
    public ResponseEntity<GenericResponse> createFeed(@RequestBody FeedDto feedDto) {
        ResponseEntity<GenericResponse> createdFeed = feedService.saveFeed(feedDto);
        GenericResponse response = new GenericResponse("feed created successfully", createdFeed);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FeedDto>> searchFeedContent(@RequestParam("term") String term) {
        List<FeedDto> searchResults = feedService.searchByFeedContent(term);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getFeedById(@PathVariable("id") Long id) {
        ResponseEntity<GenericResponse> feed = feedService.getFeedById(id);
        GenericResponse response = new GenericResponse("feed retrieved successfully", feed);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllFeeds() {
        ResponseEntity<GenericResponse> feeds = feedService.getAllFeeds();
        GenericResponse response = new GenericResponse("feeds retrieved successfully", feeds);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteFeedById(@PathVariable("id") Long id) {
        feedService.deleteFeedById(id);
        GenericResponse response = new GenericResponse("feed deleted successfully", null);
        return ResponseEntity.ok(response);
    }


}
