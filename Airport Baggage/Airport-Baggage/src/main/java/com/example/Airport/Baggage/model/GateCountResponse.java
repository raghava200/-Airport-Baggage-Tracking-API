package com.example.Airport.Baggage.model;

public class GateCountResponse {
    private String destinationGate;
    private Long uniqueBagCount;

    public GateCountResponse() {}

    public GateCountResponse(String destinationGate, Long uniqueBagCount) {
        this.destinationGate = destinationGate;
        this.uniqueBagCount = uniqueBagCount;
    }

    // Getters and Setters
    public String getDestinationGate() { return destinationGate; }
    public void setDestinationGate(String destinationGate) { this.destinationGate = destinationGate; }

    public Long getUniqueBagCount() { return uniqueBagCount; }
    public void setUniqueBagCount(Long uniqueBagCount) { this.uniqueBagCount = uniqueBagCount; }
}