package com.erabia.studentapi.model;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="students")
public class Student implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "average")
    private double average;

    public Student(String id, String name, int age, double average) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.average = average;
    }
    public Student(){}
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setAverage(double average) {
        this.average = average;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getAverage() { return average; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Double.compare(student.average, average) == 0 && id.equals(student.id) && name.equals(student.name);
    }


}
