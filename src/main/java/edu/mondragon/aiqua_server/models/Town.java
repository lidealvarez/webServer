package edu.mondragon.aiqua_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "town")
public class Town {
    @Id
    private Integer townID;

    private String description;

    @ManyToOne()
    @JoinColumn(name = "zoneID")
    private Zone zone;

    public Town() {
    }

    public Town(Integer townID, String description, Zone zone) {
        this.townID = townID;
        this.description = description;
        this.zone = zone;
    }

    public Integer getTownID() {
        return townID;
    }

    public void setTownID(Integer townID) {
        this.townID = townID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

}
