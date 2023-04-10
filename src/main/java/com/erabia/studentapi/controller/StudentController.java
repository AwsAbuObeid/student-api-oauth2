package com.erabia.studentapi.controller;

import com.erabia.studentapi.dto.ErrorResponseDTO;
import com.erabia.studentapi.exceptions.StudentException;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;
import java.util.stream.Collectors;
import com.erabia.studentapi.enums.ImplType;
import com.erabia.studentapi.stratrgy.StudentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.erabia.studentapi.enums.StudentExceptionType.UNSUPPORTED_OPERATION;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentContext studentContext;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<Student> getAll(@RequestParam ImplType implType) throws StudentException {
        List<Student> result = studentContext.getAll(implType);
        return result;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value ="/{id}", produces = "application/json")
    public Student getById(@PathVariable String id ,@RequestParam ImplType implType) throws StudentException {
        Student student= studentContext.getById(id,implType);
        return student;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search", produces = "application/json")
    public List<Student> search(
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) Double average,
                                    @RequestParam(required = false) ComparisonOperator operator,
                                    @RequestParam ImplType implType) throws StudentException {

        List<Student> result;

        if (name != null && average != null && operator!=null) {
            List<Student> byName = studentContext.getByName(name, implType);
            List<Student> byAverage = studentContext.getByAverage(average, operator, implType);
            result = byName.stream().filter(byAverage::contains).collect(Collectors.toList());
        }
        else if (name != null && average == null && operator == null)
            result = studentContext.getByName(name, implType);
        else if (name == null && average != null && operator != null)
            result = studentContext.getByAverage(average, operator, implType);
        else {
            throw new StudentException("Unsupported operation. Please provide valid" +
                    " values for name or average and operator.",UNSUPPORTED_OPERATION);
        }
        return result;
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void deleteStudent(@PathVariable  String id,@RequestParam ImplType implType) throws StudentException {
        studentContext.delete(id,implType);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping( produces = "application/json")
    public void addStudent(@RequestBody Student student,@RequestParam ImplType implType) throws StudentException {
        studentContext.add(student,implType);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping( produces = "application/json")
    public void updateStudent(@RequestBody Student student,@RequestParam ImplType implType) throws StudentException {
        studentContext.add(student,implType);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ StudentException.class })
    public ErrorResponseDTO handleStudentException(final StudentException ex) {
        ErrorResponseDTO errorListWsDTO = new ErrorResponseDTO();
        errorListWsDTO.setErrorType(ex.getType().name());
        errorListWsDTO.setErrorMessage(ex.getMessage());
        return errorListWsDTO;
    }
}

