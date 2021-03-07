package de.biobank.dataservice.controller;

import com.github.javafaker.Faker;
import com.google.common.base.Stopwatch;
import de.biobank.dataservice.dto.CreateRandomDoctor;
import de.biobank.dataservice.entity.Doctor;
import de.biobank.dataservice.entity.Profession;
import de.biobank.dataservice.mapper.DoctorMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
public class DoctorController {

    private static final int MAX_RANDOM_DOCTORS = 4096;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> getDoctor(@NonNull @PathVariable Long id) {
        Doctor doctor = doctorMapper.getDoctorById(id);
        if (doctor != null) {
            return ResponseEntity.ok().body(doctor);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/doctor")
    public Iterable<Doctor> getDoctors() {
        return doctorMapper.getDoctors();
    }

    @PostMapping("/doctor")
    public void createRandomDoctors(CreateRandomDoctor randomDoctor) throws SQLException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        if (randomDoctor.getCount() > MAX_RANDOM_DOCTORS) {
            randomDoctor.setCount(MAX_RANDOM_DOCTORS);
            log.warn("only a maximum of {} can be created per request", randomDoctor.getCount());
        }

        log.info("createRandomDoctors has started");
        if (randomDoctor.isUseBatch()) {
            insertRandomDoctors(randomDoctor);
        } else {
            insertRandomDoctorsWithMyBatis(randomDoctor);
        }
        log.info("createRandomDoctors has created doctors {} and finished in {}", randomDoctor.getCount(), stopwatch.toString());
    }

    private void insertRandomDoctorsWithMyBatis(CreateRandomDoctor randomDoctor) {
        Faker faker = new Faker(new Locale(randomDoctor.getLocale()));
        for (int i = 0; i < randomDoctor.getCount(); i++) {
            doctorMapper.insertDoctor(Doctor.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .zip(faker.address().zipCode())
                    .city(faker.address().city())
                    .profession(Profession.randomProfession())
                    .build());
        }
    }

    private void insertRandomDoctors(CreateRandomDoctor randomDoctor) throws SQLException {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                new SingleConnectionDataSource(dataSource.getConnection(), true));
        Faker faker = new Faker(new Locale(randomDoctor.getLocale()));
        List<String> sqls = new ArrayList<>();
        for (int i = 0; i < randomDoctor.getCount(); i++) {
            String sql = String.format("insert into MEDICAL_DATA.DOCTOR"
                            + " (first_name, last_name, zip, city, profession) values"
                            + " ('%s', '%s', '%s', '%s', '%s')",
                    faker.name().firstName(), faker.name().lastName(), faker.address().city(), faker.address().zipCode(), Profession.randomProfession());
            sqls.add(sql);
        }
        jdbcTemplate.batchUpdate(sqls.toArray(new String[0]));
    }

}
