package com.incedo.capstone.smartinventory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Godowns {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long godownId;
    private String location = null;
    private Double capacityInQuintals = null;
    private LocalDate startDate = null;

    @Override
    public String toString() {
        return "Godowns{" +
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
