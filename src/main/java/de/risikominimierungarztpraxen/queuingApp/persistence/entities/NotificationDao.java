package de.risikominimierungarztpraxen.queuingApp.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name="notification")
public class NotificationDao {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private ReceiverType receiverType;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private AppointmentDao appointment;

    private String address;

    public ReceiverType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(ReceiverType receiverType) {
        this.receiverType = receiverType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAppointment(AppointmentDao appointment) {
        this.appointment = appointment;
    }
}
