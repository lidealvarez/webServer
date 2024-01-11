package edu.mondragon.aiqua_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alert")
public class Alert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alertID;

    private String alertMessage;
    private String severity;
    private boolean status;

    @ManyToOne()
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "dataID")
    private Data data;

    public Alert() {
    }

    public Alert(Integer alertID, String alertMessage, String severity, boolean status, User user, Data data) {
        this.alertID = alertID;
        this.alertMessage = alertMessage;
        this.severity = severity;
        this.status = status;
        this.user = user;
        this.data = data;
    }

    public Integer getAlertID() {
        return alertID;
    }

    public void setAlertID(Integer alertID) {
        this.alertID = alertID;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
