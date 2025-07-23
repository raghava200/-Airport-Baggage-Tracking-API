package com.example.Airport.Baggage.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bag_scans")
public class BagScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scanInternalId;

    @Column(nullable = false)
    private String bagTagId;

    @Column(nullable = false)
    private String destinationGate;

    @Column(nullable = false)
    private String locationScanned;

    @Column(nullable = false)
    private LocalDateTime scanTimestamp;

    @Column(nullable = false)
    private String status = "logged";

    // Constructors
    public BagScan() {}

    public BagScan(String bagTagId, String destinationGate, String locationScanned) {
        this.bagTagId = bagTagId;
        this.destinationGate = destinationGate;
        this.locationScanned = locationScanned;
        this.scanTimestamp = LocalDateTime.now();
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