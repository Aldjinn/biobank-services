package de.biobank.dataservice.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Doctor {

    private Long id;
    private String firstName;
    private String lastName;
    private String zip;
    private String city;
    private Profession profession;

}
