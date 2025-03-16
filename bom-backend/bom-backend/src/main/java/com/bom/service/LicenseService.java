package com.bom.service;

import com.bom.dto.LicenseRequest;
import com.bom.dto.LicenseResponse;
import com.bom.model.License;
import com.bom.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    public LicenseResponse createLicense(LicenseRequest request) {
        License license = new License();
        license.setLicensekey(request.getLicenseKey());
        license.setValidTill(request.getValidTill());

        License savedLicense = licenseRepository.save(license);
        return new LicenseResponse(savedLicense.getId(), savedLicense.getLicensekey(), savedLicense.getValidTill());
    }

    public List<LicenseResponse> getAllLicenses() {
        return licenseRepository.findAll()
                .stream()
                .map(lic -> new LicenseResponse(lic.getId(), lic.getLicensekey(), lic.getValidTill()))
                .collect(Collectors.toList());
    }

    public LicenseResponse getLicenseById(Long id) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("License not found"));
        return new LicenseResponse(license.getId(), license.getLicensekey(), license.getValidTill());
    }

    public void deleteLicense(Long id) {
        licenseRepository.deleteById(id);
    }
}
