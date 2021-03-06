package de.biobank.dataservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Builder
@Data
public class Doctor {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String zip;
    private String city;
    private Profession profession;

}
