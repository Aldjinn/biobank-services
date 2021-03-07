package de.biobank.dataservice.controller;

import de.biobank.dataservice.entity.Doctor;
import de.biobank.dataservice.entity.Profession;
import de.biobank.dataservice.mapper.DoctorMapper;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private DoctorMapper doctorRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = this.port;
    }

    @Test
    void getDoctor() {
        when(doctorRepository.getDoctorById(42L)).thenReturn(Doctor.builder()
                .id(42L)
                .firstName("Max")
                .lastName("Musterarzt")
                .zip("12345")
                .city("Musterhausen")
                .profession(Profession.ALLGEMEINMEDIZIN)
                .build());

        given().log().all()
                .when().get("/doctor/42")
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("id", equalTo(42));
    }

}