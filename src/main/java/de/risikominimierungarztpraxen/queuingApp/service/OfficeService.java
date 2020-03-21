package de.risikominimierungarztpraxen.queuingApp.service;

import org.springframework.stereotype.Service;

import de.risikominimierungarztpraxen.queuingApp.model.Office;

@Service
public class OfficeService {

    public void deleteOffice(String officeId) {

    }

    public Office createOffice(Office office) {
        return office;
    }
}
