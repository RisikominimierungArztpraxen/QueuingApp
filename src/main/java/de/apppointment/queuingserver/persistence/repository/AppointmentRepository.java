package de.apppointment.queuingserver.persistence.repository;

import de.apppointment.queuingserver.persistence.entities.AppointmentDao;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<AppointmentDao, Integer> {
}
