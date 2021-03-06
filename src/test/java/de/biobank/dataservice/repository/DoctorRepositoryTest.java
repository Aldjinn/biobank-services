package de.biobank.dataservice.repository;

import de.biobank.dataservice.entity.Doctor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Slf4j
@SpringBootTest
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void findById() {
        Optional<Doctor> doctor = doctorRepository.findById(1024L);
        Assertions.assertNotNull(doctor);
        Assertions.assertNotNull(doctor.get());
        log.info("{}", doctor.get());
    }

    @Test
    public void findAll() {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        Assertions.assertNotNull(doctors);
        Assertions.assertTrue(StreamSupport.stream(doctors.spliterator(), false).count() > 0);
    }

}