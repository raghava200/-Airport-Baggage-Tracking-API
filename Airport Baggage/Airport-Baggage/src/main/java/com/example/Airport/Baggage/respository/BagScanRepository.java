package com.example.Airport.Baggage.respository;

import com.example.Airport.Baggage.model.ActiveBagResponse;
import com.example.Airport.Baggage.model.BagScan;
import com.example.Airport.Baggage.model.GateCountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BagScanRepository extends JpaRepository<BagScan, Long> {

    List<BagScan> findByBagTagIdOrderByScanTimestampDesc(String bagTagId);

    List<BagScan> findByDestinationGateOrderByScanTimestampDesc(String destinationGate);

    Optional<BagScan> findFirstByBagTagIdOrderByScanTimestampDesc(String bagTagId);

    @Query("SELECT new com.airport.baggage.dto.ActiveBagResponse(" +
            "bs.bagTagId, MAX(bs.scanTimestamp), " +
            "(SELECT bs2.locationScanned FROM BagScan bs2 WHERE bs2.bagTagId = bs.bagTagId " +
            "AND bs2.scanTimestamp = MAX(bs.scanTimestamp))) " +
            "FROM BagScan bs " +
            "WHERE bs.destinationGate = :gate AND bs.scanTimestamp >= :since " +
            "GROUP BY bs.bagTagId")
    List<ActiveBagResponse> findActiveBagsByGate(@Param("gate") String gate,
                                                 @Param("since") LocalDateTime since);

    @Query("SELECT new com.airport.baggage.dto.GateCountResponse(" +
            "bs.destinationGate, COUNT(DISTINCT bs.bagTagId)) " +
            "FROM BagScan bs " +
            "WHERE bs.scanTimestamp >= :since " +
            "GROUP BY bs.destinationGate")
    List<GateCountResponse> findGateCountsSince(@Param("since") LocalDateTime since);
}