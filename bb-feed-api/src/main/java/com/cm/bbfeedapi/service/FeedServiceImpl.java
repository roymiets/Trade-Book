package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.FeedDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.mapper.FeedMapper;
import com.cm.bbfeedapi.model.Feed;
import com.cm.bbfeedapi.repository.FeedRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final FeedRepo feedRepository;
    private final FeedMapper feedMapper;


    public ResponseEntity<GenericResponse> saveFeed(FeedDto feedDto) {
        if (feedDto == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalid request", null));
        }
        Feed feed = feedMapper.convertDtoToEntity(feedDto);
        feed = feedRepository.save(feed);
        FeedDto savedFeedDto = feedMapper.convertEntityToDto(feed);
        GenericResponse response = new GenericResponse("Feed created successfully", savedFeedDto);
        return ResponseEntity.ok(response);
    }

    public List<FeedDto> searchByFeedContent(String term) {
        List<Feed> feeds = feedRepository.findByFeedContentContaining(term);
        return feeds.stream()
                .map(feedMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }
    public ResponseEntity<GenericResponse> getFeedById(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalid request", null));
        }
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feed not found"));
       FeedDto feedDto = feedMapper.convertEntityToDto(feed);
        GenericResponse response = new GenericResponse("Feed retrieved successfully", feedDto);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GenericResponse> getAllFeeds() {
        List<Feed> feeds = feedRepository.findAll();
        List<FeedDto> feedDtos =feeds.stream()
                .map(feedMapper::convertEntityToDto)
                .collect(Collectors.toList());
        GenericResponse response = new GenericResponse("Feeds retrieved successfully", feedDtos);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GenericResponse> deleteFeedById(Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalid request", null));
        }
       feedRepository.deleteById(id);
        GenericResponse response = new GenericResponse("Feed deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
