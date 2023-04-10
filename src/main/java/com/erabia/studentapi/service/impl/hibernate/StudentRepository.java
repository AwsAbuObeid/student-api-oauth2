package com.erabia.studentapi.service.impl.hibernate;

import com.erabia.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    List<Student> findByNameContaining(String name);
    @Query("SELECT s FROM Student s WHERE "
            + "( :operator = 'EQ' AND s.average = :average ) "
            + "OR ( :operator = 'LT' AND s.average < :average ) "
            + "OR ( :operator = 'GT' AND s.average > :average ) ")
    List<Student> findByAverage(@Param("average") double average, @Param("operator") String operator);

}
