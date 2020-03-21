package de.risikominimierungarztpraxen.queuingApp.persistence.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DoctorsOffice {

    @Id
    @GeneratedValue
    private int id;

    private String officeId;

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getOfficeId() {
        return officeId;
    }
}
