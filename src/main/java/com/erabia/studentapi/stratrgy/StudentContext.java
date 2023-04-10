package com.erabia.studentapi.stratrgy;

import com.erabia.studentapi.exceptions.StudentException;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;
import com.erabia.studentapi.enums.ImplType;

import java.util.List;

public interface StudentContext {
    List<Student> getAll(ImplType implType) throws StudentException;

    Student getById(String id, ImplType implType) throws StudentException;

    List<Student> getByName(String name, ImplType implType) throws StudentException;

    List<Student> getByAverage(double average, ComparisonOperator operator, ImplType implType) throws StudentException;

    void delete(String id, ImplType implType) throws StudentException;

    void add(Student student, ImplType implType) throws StudentException;

    void update(Student student, ImplType implType) throws StudentException;

}
