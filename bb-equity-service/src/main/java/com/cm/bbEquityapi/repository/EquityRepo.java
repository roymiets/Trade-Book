package com.cm.bbEquityapi.repository;

import com.cm.bbEquityapi.model.Equity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquityRepo extends JpaRepository<Equity,Long> {
    @Query("SELECT e FROM Equity e WHERE e.symbol LIKE %:term% OR e.name LIKE %:term%")
    List<Equity> searchEquitiesBySymbolOrName(@Param("term") String term);

}
