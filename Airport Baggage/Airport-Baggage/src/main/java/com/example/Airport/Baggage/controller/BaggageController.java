package com.example.Airport.Baggage.controller;

import com.example.Airport.Baggage.model.ActiveBagResponse;
import com.example.Airport.Baggage.model.BagScanRequest;
import com.example.Airport.Baggage.model.BagScanResponse;
import com.example.Airport.Baggage.model.GateCountResponse;
import com.example.Airport.Baggage.service.BagScanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baggage")
public class BaggageController {

    @Autowired
    private BagScanService bagScanService;

    // Base functionality endpoints
    @PostMapping("/scan")
    public ResponseEntity<BagScanResponse> createBagScan(@Valid @RequestBody BagScanRequest request) {
        BagScanResponse response = bagScanService.createBagScan(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/scans/bag/{bagTagId}")
    public ResponseEntity<List<BagScanResponse>> getBagScans(@PathVariable String bagTagId) {
        List<BagScanResponse> scans = bagScanService.getBagScans(bagTagId);
        return ResponseEntity.ok(scans);
    }

    @GetMapping("/scans/gate/{destinationGate}")
    public ResponseEntity<List<BagScanResponse>> getGateScans(@PathVariable String destinationGate) {
        List<BagScanResponse> scans = bagScanService.getGateScans(destinationGate);
        return ResponseEntity.ok(scans);
    }

    // Milestone 1: Last Known Location of a Bag
    @GetMapping("/scans/bag/{bagTagId}")
    public ResponseEntity<BagScanResponse> getLatestBagScan(
            @PathVariable String bagTagId,
            @RequestParam(required = false) Boolean latest) {

        if (latest != null && latest) {
            BagScanResponse response = bagScanService.getLatestBagScan(bagTagId);
            return ResponseEntity.ok(response);
        } else {
            BagScanResponse scans = (BagScanResponse) bagScanService.getBagScans(bagTagId);
            return ResponseEntity.ok(scans);
        }
    }

    // Milestone 2: Bags Currently En-route to a Gate
    @GetMapping("/active/gate/{destinationGate}")
    public ResponseEntity<List<ActiveBagResponse>> getActiveBags(
            @PathVariable String destinationGate,
            @RequestParam(defaultValue = "60") int sinceMinutes) {

        List<ActiveBagResponse> activeBags = bagScanService.getActiveBags(destinationGate, sinceMinutes);
        return ResponseEntity.ok(activeBags);
    }

    // Milestone 3: Count Bags per Gate (Recent)
    @GetMapping("/stats/gate-counts")
    public ResponseEntity<List<GateCountResponse>> getGateCounts(
            @RequestParam(defaultValue = "60") int sinceMinutes) {

        List<GateCountResponse> gateCounts = bagScanService.getGateCounts(sinceMinutes);
        return ResponseEntity.ok(gateCounts);
    }
}
