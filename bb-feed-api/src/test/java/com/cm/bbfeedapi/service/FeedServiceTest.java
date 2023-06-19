package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.FeedDto;
import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.mapper.FeedMapper;
import com.cm.bbfeedapi.model.Feed;
import com.cm.bbfeedapi.repository.FeedRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedServiceTest {

    @Mock
    private FeedRepo feedRepository;

    @Mock
    private FeedMapper feedMapper;

    @InjectMocks
    private FeedServiceImpl feedService;

    @Test
    void saveFeed_ValidDto_ReturnsResponseEntityWithCreatedFeedDto() {
        // Arrange
        FeedDto feedDto = new FeedDto();
        Feed feed = new Feed();
        FeedDto savedFeedDto = new FeedDto();
        GenericResponse expectedResponse = new GenericResponse("Feed created successfully", savedFeedDto);

        when(feedMapper.convertDtoToEntity(feedDto)).thenReturn(feed);
        when(feedRepository.save(feed)).thenReturn(feed);
        when(feedMapper.convertEntityToDto(feed)).thenReturn(savedFeedDto);

        // Act
        ResponseEntity<GenericResponse> response = feedService.saveFeed(feedDto);


        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(feedRepository, times(1)).save(feed);
        verify(feedMapper, times(1)).convertDtoToEntity(feedDto);
        verify(feedMapper, times(1)).convertEntityToDto(feed);
    }


    @Test
    void saveFeed_NullDto_ReturnsBadRequestResponseEntity() {
        // Arrange
        FeedDto feedDto = null;
        GenericResponse expectedResponse = new GenericResponse("Invalid request", null);

        // Act
        ResponseEntity<GenericResponse> response = feedService.saveFeed(feedDto);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(feedRepository, never()).save(any(Feed.class));
        verify(feedMapper, never()).convertDtoToEntity(any(FeedDto.class));
        verify(feedMapper, never()).convertEntityToDto(any(Feed.class));
    }

    @Test
    void getFeedById_ExistingId_ReturnsResponseEntityWithFeedDto() {
        // Arrange
        Long id = 1L;
        Feed feed = new Feed();
        FeedDto feedDto = new FeedDto();
        GenericResponse expectedResponse = new GenericResponse("Feed retrieved successfully", feedDto);

        when(feedRepository.findById(id)).thenReturn(Optional.of(feed));
        when(feedMapper.convertEntityToDto(feed)).thenReturn(feedDto);

        // Act
        ResponseEntity<GenericResponse> response = feedService.getFeedById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(feedRepository, times(1)).findById(id);
        verify(feedMapper, times(1)).convertEntityToDto(feed);
    }
    @Test
    void getFeedById_NullId_ReturnsBadRequestResponseEntity() {
        // Arrange
        Long id = null;
        GenericResponse expectedResponse = new GenericResponse("Invalid request", null);

        // Act
        ResponseEntity<GenericResponse> response = feedService.getFeedById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(feedRepository, never()).findById(anyLong());
        verify(feedMapper, never()).convertEntityToDto(any(Feed.class));
    }

    @Test
    void deleteFeedById_ValidId_ReturnsResponseEntityWithNullBody() {
        // Arrange
        Long id = 1L;
        GenericResponse expectedResponse = new GenericResponse("Feed deleted successfully", null);

        // Act
        ResponseEntity<GenericResponse> response = feedService.deleteFeedById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(feedRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteFeedById_NullId_ReturnsBadRequestResponseEntity() {
        // Arrange
        Long id = null;
        GenericResponse expectedResponse = new GenericResponse("Invalid request", null);

        // Act
        ResponseEntity<GenericResponse> response = feedService.deleteFeedById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(feedRepository, never()).deleteById(anyLong());
    }


}
