package com.example.Airport.Baggage.service;

import com.example.Airport.Baggage.model.*;
import com.example.Airport.Baggage.respository.BagScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BagScanService {

    @Autowired
    private BagScanRepository bagScanRepository;

    public BagScanResponse createBagScan(BagScanRequest request) {
        BagScan bagScan = new BagScan(
                request.getBagTagId(),
                request.getDestinationGate(),
                request.getLocationScanned()
        );

        BagScan savedScan = bagScanRepository.save(bagScan);
        return new BagScanResponse(savedScan);
    }

    public List<BagScanResponse> getBagScans(String bagTagId) {
        List<BagScan> scans = bagScanRepository.findByBagTagIdOrderByScanTimestampDesc(bagTagId);
        return scans.stream()
                .map(BagScanResponse::new)
                .collect(Collectors.toList());
    }

    public List<BagScanResponse> getGateScans(String destinationGate) {
        List<BagScan> scans = bagScanRepository.findByDestinationGateOrderByScanTimestampDesc(destinationGate);
        return scans.stream()
                .map(BagScanResponse::new)
                .collect(Collectors.toList());
    }

    public BagScanResponse getLatestBagScan(String bagTagId) {
        Optional<BagScan> latestScan = bagScanRepository.findFirstByBagTagIdOrderByScanTimestampDesc(bagTagId);

        if (latestScan.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No scans found for bag tag: " + bagTagId);
        }

        return new BagScanResponse(latestScan.get());
    }

    public List<ActiveBagResponse> getActiveBags(String destinationGate, int sinceMinutes) {
        LocalDateTime since = LocalDateTime.now().minusMinutes(sinceMinutes);
        return bagScanRepository.findActiveBagsByGate(destinationGate, since);
    }

    public List<GateCountResponse> getGateCounts(int sinceMinutes) {
        LocalDateTime since = LocalDateTime.now().minusMinutes(sinceMinutes);
        return bagScanRepository.findGateCountsSince(since);
    }
}