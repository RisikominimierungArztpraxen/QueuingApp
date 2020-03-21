package de.risikominimierungarztpraxen.queuingApp.persistence.repository;

import de.risikominimierungarztpraxen.queuingApp.persistence.entities.DoctorsOffice;
import org.springframework.data.repository.CrudRepository;

public interface DoctorsOfficeRepository extends CrudRepository<DoctorsOffice, Integer> {

    DoctorsOffice findByOfficeId(String officeId);
}
