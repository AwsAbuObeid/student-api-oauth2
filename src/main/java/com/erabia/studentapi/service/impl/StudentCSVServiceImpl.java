package com.erabia.studentapi.service.impl;

import com.erabia.service.CSVFileService;
import com.erabia.service.exception.CSVException;
import com.erabia.studentapi.exceptions.*;
import com.erabia.studentapi.service.StudentService;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;
import org.springframework.beans.factory.annotation.Value;

import com.erabia.service.impl.DefaultFileService;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.erabia.studentapi.enums.StudentExceptionType.*;
import static com.erabia.studentapi.util.StudentUtil.isValidStudent;
import static com.erabia.studentapi.util.StudentUtil.studentsFromList;

@Service("CSV")
public class StudentCSVServiceImpl implements StudentService {

    @Value("${csvStudentsFile.filepath}")
    String studentFilePath;
    @Value("${csvStudentsFile.delimiter}")
    String delimiter;
    CSVFileService csvFileService = DefaultFileService.getInstance() ;

    @Override
    public List<Student> getAll() throws StudentException {
        List<Student> result;
        try {
            List<List<String>> data=csvFileService.getAll(studentFilePath,delimiter,true);
            result=studentsFromList(data);
        } catch (CSVException e) {
            throw new StudentException(e.getMessage(),CSV_EXCEPTION);
        }
        return result;
    }

    @Override
    public Student getById(String id) throws StudentException {
        try {
            List<List<String>> data=csvFileService.search(studentFilePath,delimiter,true,0,id);
            return studentsFromList(data).get(0);
        } catch (CSVException e) {
            throw new StudentException(e.getMessage(),CSV_EXCEPTION);
        }
    }

    @Override
    public List<Student> getByName(String name) throws StudentException {
        List<Student> result;
        try {
            List<List<String>> data=csvFileService.search(studentFilePath,delimiter,true,1,name);
            result=studentsFromList(data);
        } catch (CSVException e) {
            throw new StudentException(e.getMessage(),CSV_EXCEPTION);
        }
        return result;
    }

    @Override
    public List<Student> getByAverage(double average, ComparisonOperator operator) throws StudentException {
        if(!operator.equals(ComparisonOperator.EQ))
            throw new StudentException("CSV does not support gt or lt for average search.", UNSUPPORTED_OPERATION);
        List<Student> result;
        try {
            List<List<String>> data=csvFileService.search(studentFilePath,delimiter,true,3, String.valueOf(average));
            result=studentsFromList(data);
        } catch (CSVException e) {
            throw new StudentException(e.getMessage(),CSV_EXCEPTION);
        }
        return result;
    }

    @Override
    public void delete(String id) throws StudentException {
        try {
            csvFileService.delete(studentFilePath,delimiter,
                    Collections.singletonMap(0, Collections.singletonList(id)));
        } catch (CSVException e) {
            throw new StudentException(e.getMessage(),CSV_EXCEPTION);
        }
    }

    @Override
    public void add(Student student) throws StudentException {
        if(isValidStudent(student))
        try {
            csvFileService.put(studentFilePath,delimiter,
                    List.of(student.getId(),
                            student.getName(),
                            String.valueOf(student.getAge()),
                            String.valueOf(student.getAverage())));
        } catch (CSVException e) {
            throw new StudentException(e.getMessage(),CSV_EXCEPTION);
        }
    }

    @Override
    public void update(Student student) throws StudentException {
        throw new StudentException("CSV does not support update.",UNSUPPORTED_OPERATION);
    }
}
