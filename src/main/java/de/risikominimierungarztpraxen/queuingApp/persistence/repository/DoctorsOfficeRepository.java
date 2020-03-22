package de.risikominimierungarztpraxen.queuingApp.persistence.repository;

import de.risikominimierungarztpraxen.queuingApp.persistence.entities.OfficeDao;
import org.springframework.data.repository.CrudRepository;

public interface DoctorsOfficeRepository extends CrudRepository<OfficeDao, Integer> {

    OfficeDao findByOfficeId(String officeId);

    void deleteByOfficeId(String officeId);
}
