package de.risikominimierungarztpraxen.queuingApp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import de.risikominimierungarztpraxen.queuingApp.model.Office;

@Service
public class OfficeService {

    public void deleteOffice(String officeId) {

    }

    public Office createOffice(Office office) {
        return office;
    }

    public Optional<Office> findOffice(String officeId) {
        return null;
    }
}
