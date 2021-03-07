package de.biobank.dataservice.mapper;

import de.biobank.dataservice.entity.Doctor;
import de.biobank.dataservice.entity.Profession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DoctorMapperTest {

    @Autowired
    private DoctorMapper doctorRepository;

    @Test
    void insertSelectDelete() {
        doctorRepository.insertDoctor(Doctor.builder()
                .firstName("Max")
                .lastName("Musterarzt")
                .zip("12345")
                .city("Musterhausen")
                .profession(Profession.ALLGEMEINMEDIZIN)
                .build());
        Doctor doctor = doctorRepository.getDoctorByName("Max", "Musterarzt");
        Assertions.assertNotNull(doctor);
        Assertions.assertNotNull(doctorRepository.getDoctorById(doctor.getId()));
        Assertions.assertEquals(doctorRepository.getDoctorById(doctor.getId()).getId(),doctor.getId() );
        Assertions.assertFalse(doctorRepository.getDoctors().isEmpty());
        doctorRepository.deleteDoctor(doctor.getId());
        Assertions.assertNull(doctorRepository.getDoctorById(doctor.getId()));
    }
}