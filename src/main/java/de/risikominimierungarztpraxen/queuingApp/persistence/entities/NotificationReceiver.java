package de.risikominimierungarztpraxen.queuingApp.persistence.entities;

import javax.persistence.*;

@Entity
public class NotificationReceiver {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private ReceiverType receiverType;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private AppointmentEntity appointment;

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

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }
}
