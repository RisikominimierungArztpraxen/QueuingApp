package de.risikominimierungarztpraxen.queuingApp.service;

import org.springframework.stereotype.Service;

import de.risikominimierungarztpraxen.queuingApp.model.Office;
import de.risikominimierungarztpraxen.queuingApp.persistence.entities.DoctorsOffice;
import de.risikominimierungarztpraxen.queuingApp.persistence.repository.DoctorsOfficeRepository;

@Service
public class OfficeService {

    private final DoctorsOfficeRepository doctorsOfficeRepository;

    public OfficeService(DoctorsOfficeRepository doctorsOfficeRepository) {
        this.doctorsOfficeRepository = doctorsOfficeRepository;
    }

    public void deleteOffice(String officeId) {
        throw new UnsupportedOperationException("Not yet implemented");
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
