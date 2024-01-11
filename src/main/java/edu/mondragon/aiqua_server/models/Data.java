package edu.mondragon.aiqua_server.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataID;

    private Date timestamp;
    private Integer flow;
    private double pressure;

    @ManyToOne()
    @JoinColumn(name = "reductorID")
    private Reductor reductor;

    public Data() {
    }

    public Data(Integer dataID, Date timestamp, Integer flow, double pressure, Reductor reductor) {
        this.dataID = dataID;
        this.timestamp = timestamp;
        this.flow = flow;
        this.pressure = pressure;
        this.reductor = reductor;
    }

    public Integer getDataID() {
        return dataID;
    }

    public void setDataID(Integer dataID) {
        this.dataID = dataID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Reductor getReductor() {
        return reductor;
    }

    public void setReductor(Reductor reductor) {
        this.reductor = reductor;
    }

    

}
