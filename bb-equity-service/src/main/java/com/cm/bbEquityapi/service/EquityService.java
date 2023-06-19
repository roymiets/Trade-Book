package com.cm.bbEquityapi.service;

import com.cm.bbEquityapi.dto.EquityDto;
import com.cm.bbEquityapi.dto.GenericResponse;
import com.cm.bbEquityapi.enums.EquityStatus;
import com.cm.bbEquityapi.model.Equity;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EquityService {
    ResponseEntity<GenericResponse> createEquity(EquityDto equityDto);

    @Transactional
    ResponseEntity<GenericResponse> saveAllEquities(List<EquityDto> equityDtoList);


    List<EquityDto> searchEquities(String searchTerm);

    ResponseEntity<GenericResponse> updateEquityDetails(Long id, EquityDto equityDto);

    List<Equity> getAllEquities(Integer pageNo, Integer pageSize, String sortBy);

    Optional<Equity> getEquityById(Long id);

    ResponseEntity<GenericResponse> deleteEquityById(Long id);

    void deleteAllEquities();

    void updateEquityStatus(Long id, EquityStatus status);
}
