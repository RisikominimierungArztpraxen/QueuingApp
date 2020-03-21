package de.risikominimierungarztpraxen.queuingApp.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "officeId")
    private DoctorsOffice doctorsOffice;

    private LocalDateTime time;
    private String patientId;
    private Integer estimatedInMinutes;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Integer getEstimatedInMinutes() {
        return estimatedInMinutes;
    }

    public void setEstimatedInMinutes(Integer estimatedInMinutes) {
        this.estimatedInMinutes = estimatedInMinutes;
    }
}
