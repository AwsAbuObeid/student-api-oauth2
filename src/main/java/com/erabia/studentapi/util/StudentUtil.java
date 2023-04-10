package com.erabia.studentapi.util;

import com.erabia.studentapi.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentUtil {
    private StudentUtil() {}
    public static List<Student> studentsFromList(List<List<String>> data) {
        List<Student> result=new ArrayList<>();
        data.remove(0);
        for(List<String> row:data){
            Student s=new Student(row.get(0),row.get(1),Integer.parseInt(row.get(2)),Double.parseDouble(row.get(3)));
            result.add(s);
        }
        return result;
    }
    public static boolean isValidStudent(Student student){
        return student.getAge()>0
                && student.getAge()<100
                && student.getAverage()>=0
                && student.getAverage()<=100
                && student.getName().length()>=3
                && student.getName().length()<100;
    }
}
