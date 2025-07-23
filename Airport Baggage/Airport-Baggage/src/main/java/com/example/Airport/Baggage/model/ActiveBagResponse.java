package com.example.Airport.Baggage.model;



import java.time.LocalDateTime;

public class ActiveBagResponse {
    private String bagTagId;
    private LocalDateTime lastScanAt;
    private String lastLocation;

    public ActiveBagResponse() {}

    public ActiveBagResponse(String bagTagId, LocalDateTime lastScanAt, String lastLocation) {
        this.bagTagId = bagTagId;
        this.lastScanAt = lastScanAt;
        this.lastLocation = lastLocation;
    } public String getBagTagId() { return bagTagId; }
    public void setBagTagId(String bagTagId) { this.bagTagId = bagTagId; }

    public LocalDateTime getLastScanAt() { return lastScanAt; }
    public void setLastScanAt(LocalDateTime lastScanAt) { this.lastScanAt = lastScanAt; }

    public String getLastLocation() { return lastLocation; }
    public void setLastLocation(String lastLocation) { this.lastLocation = lastLocation; }
}