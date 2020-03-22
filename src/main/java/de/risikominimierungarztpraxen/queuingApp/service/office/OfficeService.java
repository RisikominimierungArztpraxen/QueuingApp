package de.risikominimierungarztpraxen.queuingApp.service.office;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.risikominimierungarztpraxen.queuingApp.rest.model.OfficeDto;
import de.risikominimierungarztpraxen.queuingApp.persistence.entities.OfficeDao;
import de.risikominimierungarztpraxen.queuingApp.persistence.repository.DoctorsOfficeRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OfficeService {

    private static final Logger LOG = LoggerFactory.getLogger(OfficeService.class);
    private final DoctorsOfficeRepository doctorsOfficeRepository;

    public OfficeService(DoctorsOfficeRepository doctorsOfficeRepository) {
        this.doctorsOfficeRepository = doctorsOfficeRepository;
    }

    public void deleteOffice(String officeId) {
        LOG.debug("Deleting office with id " + officeId);
        doctorsOfficeRepository.deleteByOfficeId(officeId);
    }

    public OfficeDto createOffice(OfficeDto office) {
        LOG.debug("Creating office with id " + office.getId());
        OfficeDao officeDao = mapToDao(office);
        doctorsOfficeRepository.save(officeDao);
        return office;
    }

    public OfficeDto findOffice(String officeId) {
        OfficeDao officeDao = doctorsOfficeRepository.findByOfficeId(officeId);
        return mapToDTO(officeDao);
    }

    private OfficeDao mapToDao(OfficeDto office) {
        if (office == null) {
            return null;
        }

        OfficeDao officeDao = new OfficeDao();
        officeDao.setOfficeId(office.getId());
        return officeDao;
    }

    private OfficeDto mapToDTO(OfficeDao officeDao) {
        if (officeDao == null) {
            return null;
        }

        OfficeDto office = new OfficeDto();
        office.setId(officeDao.getOfficeId());
        return office;
    }
}
