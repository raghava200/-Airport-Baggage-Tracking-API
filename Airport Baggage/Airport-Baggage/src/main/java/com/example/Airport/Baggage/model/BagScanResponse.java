package com.example.Airport.Baggage.model;

import com.example.Airport.Baggage.model.BagScan;
import java.time.LocalDateTime;

public class BagScanResponse {
    private Long scanInternalId;
    private String bagTagId;
    private String destinationGate;
    private String locationScanned;
    private LocalDateTime scanTimestamp;
    private String status;

    public BagScanResponse() {}

    public BagScanResponse(BagScan bagScan) {
        this.scanInternalId = bagScan.getScanInternalId();
        this.bagTagId = bagScan.getBagTagId();
        this.destinationGate = bagScan.getDestinationGate();
        this.locationScanned = bagScan.getLocationScanned();
        this.scanTimestamp = bagScan.getScanTimestamp();
        this.status = bagScan.getStatus();
    }

    // Getters and Setters
    public Long getScanInternalId() { return scanInternalId; }
    public void setScanInternalId(Long scanInternalId) { this.scanInternalId = scanInternalId; }

    public String getBagTagId() { return bagTagId; }
    public void setBagTagId(String bagTagId) { this.bagTagId = bagTagId; }

    public String getDestinationGate() { return destinationGate; }
    public void setDestinationGate(String destinationGate) { this.destinationGate = destinationGate; }

    public String getLocationScanned() { return locationScanned; }
    public void setLocationScanned(String locationScanned) { this.locationScanned = locationScanned; }

    public LocalDateTime getScanTimestamp() { return scanTimestamp; }
    public void setScanTimestamp(LocalDateTime scanTimestamp) { this.scanTimestamp = scanTimestamp; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

