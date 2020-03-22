package de.apppointment.queuingserver.persistence.entities;


import javax.persistence.*;

@Entity
@Table(name="office")
public class OfficeDao {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String officeId;

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getOfficeId() {
        return officeId;
    }
}
