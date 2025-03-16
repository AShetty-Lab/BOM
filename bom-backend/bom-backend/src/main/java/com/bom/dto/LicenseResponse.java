package com.bom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LicenseResponse {
    private Long id;
    private String licenseKey;
    private LocalDate validTill;

    public LicenseResponse(Long id, String licenseKey, LocalDate validTill) {
        this.id = id;
        this.licenseKey = licenseKey;
        this.validTill = validTill;
    }
}
