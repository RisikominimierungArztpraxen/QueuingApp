package de.risikominimierungarztpraxen.queuingApp.persistence.repository;

import de.risikominimierungarztpraxen.queuingApp.persistence.entities.AppointmentDao;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<AppointmentDao, Integer> {
}
