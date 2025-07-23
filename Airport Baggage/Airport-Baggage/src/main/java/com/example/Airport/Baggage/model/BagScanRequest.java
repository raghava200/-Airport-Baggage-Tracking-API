package com.example.Airport.Baggage.model;

import jakarta.validation.constraints.NotBlank;

public class BagScanRequest {

    @NotBlank(message = "Bag tag ID is required")
    private String bagTagId;

    @NotBlank(message = "Destination gate is required")
    private String destinationGate;

    @NotBlank(message = "Location scanned is required")
    private String locationScanned;

    // Constructors
    public BagScanRequest() {}

    public BagScanRequest(String bagTagId, String destinationGate, String locationScanned) {
        this.bagTagId = bagTagId;
        this.destinationGate = destinationGate;
        this.locationScanned = locationScanned;
    }

    // Getters and Setters
    public String getBagTagId() { return bagTagId; }
    public void setBagTagId(String bagTagId) { this.bagTagId = bagTagId; }

    public String getDestinationGate() { return destinationGate; }
    public void setDestinationGate(String destinationGate) { this.destinationGate = destinationGate; }

    public String getLocationScanned() { return locationScanned; }
    public void setLocationScanned(String locationScanned) { this.locationScanned = locationScanned; }
}