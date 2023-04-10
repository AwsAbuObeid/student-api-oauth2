package com.erabia.studentapi.service.impl;

import com.erabia.studentapi.enums.StudentExceptionType;
import com.erabia.studentapi.exceptions.StudentException;
import com.erabia.studentapi.service.StudentService;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;
import com.erabia.studentapi.service.impl.hibernate.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.erabia.studentapi.enums.StudentExceptionType.ID_NOT_FOUND;
import static com.erabia.studentapi.enums.StudentExceptionType.INVALID_STUDENT_VALUES;
import static com.erabia.studentapi.util.StudentUtil.isValidStudent;

@Service("hibernate")
public class StudentHibernateServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getByName(String name) {
        return studentRepository.findByNameContaining(name);
    }

    @Override
    public List<Student> getByAverage(double average, ComparisonOperator operator) {
        return studentRepository.findByAverage(average,operator.name());
    }

    @Override
    public void delete(String id) throws StudentException {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            throw new StudentException("student not found with ID: " + id,ID_NOT_FOUND);
        }
        studentRepository.deleteById(id);
    }


    @Override
    public void add(Student student) throws StudentException {
        if(isValidStudent(student))
            studentRepository.save(student);
        else throw new StudentException("Could not add student, values are invalid.",INVALID_STUDENT_VALUES);

    }

    @Override
    public void update(Student student) throws StudentException {
        if (isValidStudent(student)) {
            Student existingStudent = studentRepository.findById(student.getId()).orElse(null);
            if (existingStudent == null)
                throw new StudentException("student not found with ID: " + student.getId(),ID_NOT_FOUND);
            existingStudent.setName(student.getName());
            existingStudent.setAge(student.getAge());
            existingStudent.setAverage(student.getAverage());
            studentRepository.save(existingStudent);
        } else {
            throw new StudentException("Could not update student, values are invalid.", INVALID_STUDENT_VALUES);
        }
    }
}
