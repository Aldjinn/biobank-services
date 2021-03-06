package de.biobank.dataservice.controller;

import de.biobank.dataservice.entity.Doctor;
import de.biobank.dataservice.repository.DoctorRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> getDoctor(@NonNull @PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            log.info("doctor {}", doctor.get());
            return ResponseEntity.ok().body(doctor.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/doctor")
    public Iterable<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

}
