package com.bom.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "licenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class License {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    //private LocalDate validTill;
    private LocalDate validTill;
    @Setter
    @Getter
    private String type;

    public String getLicensekey() {
        return Licensekey;
    }

    public void setLicensekey(String licensekey) {
        Licensekey = licensekey;
    }

    @Getter
    @Setter
    @Column(name = "Licensekey") // Optional: Ensures proper DB column naming
    private String Licensekey;

    public LocalDate getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
    }
}
