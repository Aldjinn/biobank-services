package de.biobank.dataservice.controller;

import de.biobank.dataservice.entity.Doctor;
import de.biobank.dataservice.entity.Profession;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DoctorController {

    @GetMapping("/doctor/{id}")
    public Doctor getDoctor(@NonNull @PathVariable Long id) {
        log.info("getDoctor {}", id);
        return Doctor.builder()
                .id(id)
                .firstName("Max")
                .lastName("Musterarzt")
                .zip("12345")
                .city("Musterhausen")
                .profession(Profession.ALLGEMEINMEDIZIN)
                .build();
    }

}
