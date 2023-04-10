package com.erabia.studentapi.stratrgy.Impl;

import com.erabia.studentapi.exceptions.StudentException;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;
import com.erabia.studentapi.service.StudentService;
import com.erabia.studentapi.stratrgy.StudentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyStudentHibernateImpl implements StudentStrategy {

    @Autowired
    @Qualifier("hibernate")
    private StudentService studentService;

    @Override
    public List<Student> getAll() throws StudentException {
        return studentService.getAll();
    }

    @Override
    public Student getById(String id) throws StudentException {
        return studentService.getById(id);
    }

    @Override
    public List<Student> getByName(String name) throws StudentException {
        return studentService.getByName(name);
    }

    @Override
    public List<Student> getByAverage(double average, ComparisonOperator operator) throws StudentException {
        return studentService.getByAverage(average, operator);
    }

    @Override
    public void delete(String id) throws StudentException {
        studentService.delete(id);
    }

    @Override
    public void add(Student student) throws StudentException {
        studentService.add(student);
    }

    @Override
    public void update(Student student) throws StudentException {
        studentService.update(student);
    }
}
