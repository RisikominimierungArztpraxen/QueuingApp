package de.apppointment.queuingserver.persistence.repository;

import de.apppointment.queuingserver.persistence.entities.OfficeDao;
import org.springframework.data.repository.CrudRepository;

public interface DoctorsOfficeRepository extends CrudRepository<OfficeDao, Integer> {

    OfficeDao findByOfficeId(String officeId);

    void deleteByOfficeId(String officeId);
}
