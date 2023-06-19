package com.cm.bbfeedapi.service;

import com.cm.bbfeedapi.dto.GenericResponse;
import com.cm.bbfeedapi.dto.ReactionDto;
import com.cm.bbfeedapi.enums.ReactionType;
import com.cm.bbfeedapi.mapper.ReactionMapper;
import com.cm.bbfeedapi.model.Reaction;
import com.cm.bbfeedapi.repository.ReactionRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReactionServiceTest {
    @Mock
    private ReactionRepo reactionRepo;

    @Mock
    private ReactionMapper reactionMapper;

    @InjectMocks
    private ReactionServiceImpl reactionService;


    @Test
    public void saveReaction_ValidDto_ReturnsOkResponse() {
        // Arrange
        ReactionDto reactionDto = new ReactionDto();
        reactionDto.setFeedId(1L);
        reactionDto.setUserId(1L);
        reactionDto.setType(ReactionType.LIKE);

        Reaction reaction = new Reaction();
        reaction.setId(1L);
        reaction.setFeedId(reactionDto.getFeedId());
        reaction.setUserId(reactionDto.getUserId());
        reaction.setType(reactionDto.getType());


        when(reactionMapper.convertDtoToEntity(reactionDto)).thenReturn(reaction);
        when(reactionMapper.convertEntityToDto(reaction)).thenReturn(reactionDto);
        when(reactionRepo.save(reaction)).thenReturn(reaction);

        // Act
        ResponseEntity<GenericResponse> response = reactionService.saveReaction(reactionDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reaction save successfully", response.getBody().getMsg());

        verify(reactionMapper, times(1)).convertDtoToEntity(reactionDto);
        verify(reactionMapper, times(1)).convertEntityToDto(reaction);
        verify(reactionRepo, times(1)).save(reaction);
    }

//    @Test
//    public void saveReaction_NullDto_ReturnsBadRequestResponse() {
//        // Act
//        ResponseEntity<GenericResponse> response = reactionService.saveReaction(null);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("invalid request", response.getBody().getMsg());
//
//        assertEquals(null, response.getBody().getPayload());
//
//        verifyNoInteractions(reactionMapper);
//        verifyNoInteractions(reactionRepo);
//    }

    @Test
    public void getReactionById_ValidId_ReturnsOkResponse() {
        // Arrange
        Long id = 1L;
        Reaction reaction = new Reaction();
        reaction.setId(id);
        reaction.setFeedId(1L);
        reaction.setUserId(1L);
        reaction.setType(ReactionType.LIKE);


        ReactionDto reactionDto = new ReactionDto();
        reactionDto.setId(id);
        reactionDto.setFeedId(reaction.getFeedId());
        reactionDto.setUserId(reaction.getUserId());
        reactionDto.setType(reaction.getType());

        when(reactionRepo.findById(id)).thenReturn(Optional.of(reaction));
        when(reactionMapper.convertEntityToDto(reaction)).thenReturn(reactionDto);

        // Act
        ResponseEntity<GenericResponse> response = reactionService.getReactionById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reaction retrieved successfully", response.getBody().getMsg());
        assertEquals(reactionDto, response.getBody().getPayload());

        verify(reactionRepo, times(1)).findById(id);
        verify(reactionMapper, times(1)).convertEntityToDto(reaction);
    }

    @Test
    public void getReactionById_NullId_ReturnsBadRequestResponse() {
        // Act
        ResponseEntity<GenericResponse> response = reactionService.getReactionById(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("invalid request", response.getBody().getMsg());
        assertEquals(null, response.getBody().getPayload());

        verifyNoInteractions(reactionMapper);
        verifyNoInteractions(reactionRepo);
    }

//    @Test
//    public void getReactionById_NonExistentId_ThrowsRuntimeException() {
//        // Arrange
//        Long id = 1L;
//        when(reactionRepo.findById(id)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(
//                RuntimeException.class,
//                () -> reactionService.getReactionById(id)
//        );
//        assertEquals("Reaction not found", exception.getMessage());
//
//        verify(reactionRepo, times(1)).findById(id);
//        verifyNoInteractions(reactionMapper);
//    }

    @Test
    public void getAllReactions_ReturnsOkResponseWithReactionsList() {
        // Arrange
        List<Reaction> reactions = new ArrayList<>();
        Reaction reaction1 = new Reaction();
        reaction1.setId(1L);
        reaction1.setFeedId(1L);
        reaction1.setUserId(1L);
        reaction1.setType(ReactionType.LIKE);
        reactions.add(reaction1);

        Reaction reaction2 = new Reaction();
        reaction2.setId(2L);
        reaction2.setFeedId(2L);
        reaction2.setUserId(1L);
        reaction2.setType(ReactionType.LOVE);
        reactions.add(reaction2);

        List<ReactionDto> reactionDtos = new ArrayList<>();
        ReactionDto reactionDto1 = new ReactionDto();
        reactionDto1.setId(1L);
        reactionDto1.setFeedId(reaction1.getFeedId());
        reactionDto1.setUserId(reaction1.getUserId());
        reactionDto1.setType(reaction1.getType());
        reactionDtos.add(reactionDto1);

        ReactionDto reactionDto2 = new ReactionDto();
        reactionDto2.setId(2L);
        reactionDto2.setFeedId(reaction2.getFeedId());
        reactionDto2.setUserId(reaction2.getUserId());
        reactionDto2.setType(reaction2.getType());
        reactionDtos.add(reactionDto2);

        when(reactionRepo.findAll()).thenReturn(reactions);
        when(reactionMapper.convertEntityToDto(reaction1)).thenReturn(reactionDto1);
        when(reactionMapper.convertEntityToDto(reaction2)).thenReturn(reactionDto2);

        // Act
        ResponseEntity<GenericResponse> response = reactionService.getAllReactions();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reactions retrieved successfully", response.getBody().getMsg());
        assertEquals(reactionDtos, response.getBody().getPayload());

        verify(reactionRepo, times(1)).findAll();
        verify(reactionMapper, times(1)).convertEntityToDto(reaction1);
        verify(reactionMapper, times(1)).convertEntityToDto(reaction2);
    }

    public void deleteReactionById_ValidId_ReturnsOkResponse() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<GenericResponse> response = reactionService.deleteReactionById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reaction deleted successfully", response.getBody().getMsg());
        assertEquals(null, response.getBody().getPayload());

        verify(reactionRepo, times(1)).deleteById(id);
        verifyNoInteractions(reactionMapper);
    }

    @Test
    public void deleteReactionById_NullId_ReturnsBadRequestResponse() {
        // Act
        ResponseEntity<GenericResponse> response = reactionService.deleteReactionById(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("invalid request", response.getBody().getMsg());
        assertEquals(null, response.getBody().getPayload());

        verifyNoInteractions(reactionMapper);
        verifyNoInteractions(reactionRepo);
    }

}
