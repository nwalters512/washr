package com.nwalters.washr.models;

public class Machine {
    int port;
    String label;
    String description;
    String status;
    String startTime;
    String timeRemaining;

    @Override
    public String toString() {
        return description + " " + status;
    }
}
