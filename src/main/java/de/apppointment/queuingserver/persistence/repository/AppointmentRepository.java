package de.apppointment.queuingserver.persistence.repository;

import de.apppointment.queuingserver.persistence.entities.AppointmentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentDao, Integer> {
    List<AppointmentDao> findAllByOrderByTime();
}
