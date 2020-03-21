package de.risikominimierungarztpraxen.queuingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.risikominimierungarztpraxen.queuingApp.persistence.entities.DoctorsOffice;
import de.risikominimierungarztpraxen.queuingApp.persistence.repository.DoctorsOfficeRepository;
import org.springframework.stereotype.Service;

import de.risikominimierungarztpraxen.queuingApp.model.Office;

@Service
public class OfficeService {

    private final DoctorsOfficeRepository doctorsOfficeRepository;
    private final List<Office> offices = new ArrayList<>();

    public OfficeService(DoctorsOfficeRepository doctorsOfficeRepository) {
        this.doctorsOfficeRepository = doctorsOfficeRepository;
    }

    public void deleteOffice(String officeId) {
        this.offices.removeIf(office -> office.getId().equals(officeId));
    }

    public Office createOffice(Office office) {
        DoctorsOffice doctorsOffice = mapToDBEntity(office);
        doctorsOfficeRepository.save(doctorsOffice);
        return office;
    }

    public Office findOffice(String officeId) {
        DoctorsOffice doctorsOffice = doctorsOfficeRepository.findByOfficeId(officeId);
        return mapToDTO(doctorsOffice);
    }

    private DoctorsOffice mapToDBEntity(Office office) {
        if (office == null) {
            return null;
        }

        DoctorsOffice doctorsOffice = new DoctorsOffice();
        doctorsOffice.setOfficeId(office.getId());
        return doctorsOffice;
    }

    private Office mapToDTO(DoctorsOffice doctorsOffice) {
        if (doctorsOffice == null) {
            return null;
        }

        Office office = new Office();
        office.setId(doctorsOffice.getOfficeId());
        return office;
    }
}
