package com.bom.controller;

import com.bom.model.License;
import com.bom.repository.LicenseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    private final LicenseRepository licenseRepository;

    public LicenseController(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @GetMapping
    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<License> getLicenseById(@PathVariable Long id) {
        Optional<License> license = licenseRepository.findById(id);
        return license.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public License createLicense(@RequestBody License license) {
        return licenseRepository.save(license);
    }

    @PutMapping("/{id}")
    public ResponseEntity<License> updateLicense(@PathVariable Long id, @RequestBody License updatedLicense) {
        return licenseRepository.findById(id)
                .map(lic -> {
                    lic.setType(updatedLicense.getType());
                    lic.setLicensekey(updatedLicense.getLicensekey());
                    return ResponseEntity.ok(licenseRepository.save(lic));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long id) {
        if (licenseRepository.existsById(id)) {
            licenseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
