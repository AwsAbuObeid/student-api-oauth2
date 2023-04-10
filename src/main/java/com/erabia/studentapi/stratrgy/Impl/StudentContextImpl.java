package com.erabia.studentapi.stratrgy.Impl;

import com.erabia.studentapi.exceptions.StudentException;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;
import com.erabia.studentapi.enums.ImplType;
import com.erabia.studentapi.service.StudentService;
import com.erabia.studentapi.stratrgy.StudentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentContextImpl implements StudentContext {
    private final Map<ImplType, StudentService> strategyMap;
    @Autowired
    public StudentContextImpl(ApplicationContext applicationContext) {
        Map<String, StudentService> beansOfType = applicationContext.getBeansOfType(StudentService.class);
        strategyMap=new HashMap<>();
        for (StudentService studentService : beansOfType.values()) {
            strategyMap.put(ImplType.fromClass(studentService.getClass()), studentService);
        }
    }

    public List<Student> getAll(ImplType implType) throws StudentException {
        return strategyMap.get(implType).getAll();
    }
    public Student getById(String id, ImplType implType) throws StudentException {
        return strategyMap.get(implType).getById(id);
    }
    public List<Student> getByName(String name, ImplType implType) throws StudentException {
        return strategyMap.get(implType).getByName(name);
    }
    public List<Student> getByAverage(double average, ComparisonOperator operator, ImplType implType) throws StudentException {
        return strategyMap.get(implType).getByAverage(average,operator);
    }
    public void delete(String id, ImplType implType) throws StudentException {
        strategyMap.get(implType).delete(id);
    }
    public void add(Student student, ImplType implType) throws StudentException {
        strategyMap.get(implType).add(student);
    }
    @Override
    public void update(Student student, ImplType implType) throws StudentException {
        strategyMap.get(implType).update(student);

    }
}
