package edu.mondragon.aiqua_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reductor")
public class Reductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reductorID;

    private String name;
    private double sensibility;

    @ManyToOne()
    @JoinColumn(name = "townID")
    private Town town;

    public Reductor() {
    }

    public Reductor(Integer reductorID, String name, double sensibility, Town town) {
        this.reductorID = reductorID;
        this.name = name;
        this.sensibility = sensibility;
        this.town = town;
    }

    public Integer getReductorID() {
        return reductorID;
    }

    public void setReductorID(Integer reductorID) {
        this.reductorID = reductorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSensibility() {
        return sensibility;
    }

    public void setSensibility(double sensibility) {
        this.sensibility = sensibility;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

}
