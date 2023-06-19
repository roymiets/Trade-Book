package com.cm.bbfeedapi.mapper;

import com.cm.bbfeedapi.dto.FeedDto;
import com.cm.bbfeedapi.dto.ReactionDto;
import com.cm.bbfeedapi.enums.ReactionType;
import com.cm.bbfeedapi.model.Feed;
import com.cm.bbfeedapi.model.Reaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReactionMapperTest {
    private ReactionMapper reactionMapper = Mappers.getMapper(ReactionMapper.class);

    @Test
    public void testConvertDtoToEntity() {
        // Arrange
        ReactionDto reactionDto = new ReactionDto();
        reactionDto.setId(1L);
        reactionDto.setFeedId(12345L);
        reactionDto.setUserId(56789L);
        reactionDto.setType(ReactionType.LIKE);


        // Act
        Reaction reaction = reactionMapper.convertDtoToEntity(reactionDto);

        // Assert
        assertEquals(reactionDto.getId(), reaction.getId());
        assertEquals(reactionDto.getFeedId(), reaction.getFeedId());
        assertEquals(reactionDto.getUserId(), reaction.getUserId());
        assertEquals(reactionDto.getType(), reaction.getType());
    }

    @Test
    public void testConvertEntityToDto() {
        // Arrange
        Reaction reaction = new Reaction();
        reaction.setId(1L);
        reaction.setFeedId(12345L);
        reaction.setUserId(56789L);
        reaction.setType(ReactionType.LIKE);

        // Act
        ReactionDto reactionDto = reactionMapper.convertEntityToDto(reaction);

        // Assert
        assertEquals(reaction.getId(), reactionDto.getId());
        assertEquals(reaction.getFeedId(), reactionDto.getFeedId());
        assertEquals(reaction.getUserId(), reactionDto.getUserId());
        assertEquals(reaction.getType(), reactionDto.getType());

    }

}
