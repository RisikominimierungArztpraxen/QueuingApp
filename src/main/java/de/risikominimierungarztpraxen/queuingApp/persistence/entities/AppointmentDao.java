package de.risikominimierungarztpraxen.queuingApp.persistence.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Appointment")
public class AppointmentDao {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "officeId")
    private OfficeDao officeDao;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Set<NotificationDao> notificationDaos;

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

    public OfficeDao getOfficeDao() {
        return officeDao;
    }

    public void setOfficeDao(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    public void setNotificationDaos(Set<NotificationDao> receivers) {
        notificationDaos = receivers;
        notificationDaos.forEach(r -> r.setAppointment(this));
    }
}
