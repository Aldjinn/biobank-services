package de.biobank.dataservice.mapper;

import de.biobank.dataservice.entity.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorMapper {

    @Select("SELECT * FROM MEDICAL_DATA.DOCTOR WHERE id = #{id}")
    Doctor getDoctorById(@Param("id") Long id);

    @Select("SELECT * FROM MEDICAL_DATA.DOCTOR WHERE first_name = #{firstName} and last_name = #{lastName}")
    Doctor getDoctorByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Select("SELECT * FROM MEDICAL_DATA.DOCTOR ORDER by id")
    List<Doctor> getDoctors();

    @Insert("INSERT into MEDICAL_DATA.DOCTOR(first_name, last_name, zip, city, profession) VALUES(#{firstName}, #{lastName}, #{zip}, #{city}, #{profession})")
    void insertDoctor(Doctor doctor);

    @Delete("DELETE FROM MEDICAL_DATA.DOCTOR WHERE id = #{id}")
    void deleteDoctor(@Param("id") Long id);

}
