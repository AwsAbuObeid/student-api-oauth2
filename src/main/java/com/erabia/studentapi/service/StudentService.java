package com.erabia.studentapi.service;


import com.erabia.studentapi.exceptions.StudentException;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll() throws StudentException;

    Student getById(String id) throws StudentException;

    List<Student> getByName(String name) throws StudentException;

    List<Student> getByAverage(double average, ComparisonOperator operator) throws StudentException;

    void delete(String id) throws StudentException;

    void add(Student student) throws StudentException;

    void update(Student student) throws StudentException;
}
