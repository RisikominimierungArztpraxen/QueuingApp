package de.risikominimierungarztpraxen.queuingApp.persistence.repository;

import de.risikominimierungarztpraxen.queuingApp.persistence.entities.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Integer> {
}
