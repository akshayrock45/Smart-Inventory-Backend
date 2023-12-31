package com.incedo.capstone.smartinventory.dto;

import java.time.LocalDate;

public class GodownsDTO {

    private long godownId;
    private String location;
    private Double capacityInQuintals;
    private LocalDate startDate;


    @Override
    public String toString() {
        return "GodownsDTO{" +
                "godownId=" + godownId +
                ", location='" + location + '\'' +
                ", capacityInQuintals=" + capacityInQuintals +
                ", startDate=" + startDate +
                '}';
    }

    public long getGodownId() {
        return godownId;
    }

    public void setGodownId(long godownId) {
        this.godownId = godownId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getCapacityInQuintals() {
        return capacityInQuintals;
    }

    public void setCapacityInQuintals(Double capacityInQuintals) {
        this.capacityInQuintals = capacityInQuintals;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
