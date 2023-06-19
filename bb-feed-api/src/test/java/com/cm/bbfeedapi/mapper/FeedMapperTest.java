package com.cm.bbfeedapi.mapper;

import com.cm.bbfeedapi.dto.FeedDto;
import com.cm.bbfeedapi.model.Feed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FeedMapperTest {
    private FeedMapper feedMapper = Mappers.getMapper(FeedMapper.class);

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        FeedDto feedDto = new FeedDto();
        feedDto.setId(1L);
        feedDto.setFeedContent("Sample content");

        // Act
        Feed feed = feedMapper.convertDtoToEntity(feedDto);

        // Assert
        Assertions.assertEquals(feedDto.getId(), feed.getId());
        Assertions.assertEquals(feedDto.getFeedContent(), feed.getFeedContent());
        // Verify other mappings as needed
    }

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        Feed feed = new Feed();
        feed.setId(1L);
        feed.setFeedContent("Sample content");

        // Act
        FeedDto feedDto = feedMapper.convertEntityToDto(feed);

        // Assert
        Assertions.assertEquals(feed.getId(), feedDto.getId());
        Assertions.assertEquals(feed.getFeedContent(), feedDto.getFeedContent());
        // Verify other mappings as needed
    }
}
