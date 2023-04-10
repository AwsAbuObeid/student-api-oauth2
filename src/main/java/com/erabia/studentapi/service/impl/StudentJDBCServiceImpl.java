package com.erabia.studentapi.service.impl;

import com.erabia.studentapi.exceptions.StudentException;
import com.erabia.studentapi.enums.ComparisonOperator;
import com.erabia.studentapi.model.Student;
import com.erabia.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.erabia.studentapi.enums.StudentExceptionType.ID_NOT_FOUND;
import static com.erabia.studentapi.enums.StudentExceptionType.JDBC_SQL_EXCEPTION;

@Service("JDBC")
public class StudentJDBCServiceImpl implements StudentService {
    @Autowired
    DataSource dataSource;
    private boolean isInitialized = false;

    private void initializeDB() throws StudentException {
        if (isInitialized) return;
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS students (" +
                    "id VARCHAR(255) PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "age INT NOT NULL," +
                    "average DOUBLE NOT NULL)";
            statement.executeUpdate(query);
            isInitialized=true;
        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
    }
    @Override
    public List<Student> getAll() throws StudentException {
        initializeDB();
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double average = resultSet.getDouble("average");
                Student student = new Student(id, name, age, average);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
        return students;
    }

    @Override
    public Student getById(String id) throws StudentException {
        initializeDB();
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double average = resultSet.getDouble("average");
                return new Student(id, name, age, average);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
    }

    @Override
    public List<Student> getByName(String name) throws StudentException {
        initializeDB();
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE name LIKE ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String studentName = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double average = resultSet.getDouble("average");
                Student student = new Student(id, studentName, age, average);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
        return students;
    }


    @Override
    public List<Student> getByAverage(double average, ComparisonOperator operator) throws StudentException {
        initializeDB();
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE average "+operator.getOp()+" ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, average);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double studentAverage = resultSet.getDouble("average");
                Student student = new Student(id, name, age, studentAverage);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
        return students;
    }


    @Override
    public void delete(String id) throws StudentException {
        initializeDB();
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,id);
            int rowsDeleted=statement.executeUpdate();
            if (rowsDeleted == 0)
                throw new StudentException("Student not found with ID: " + id,ID_NOT_FOUND);
        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
    }

    @Override
    public void add(Student student) throws StudentException {
        initializeDB();
        String query="INSERT INTO students (id, name, age, average) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setDouble(4, student.getAverage());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
    }

    @Override
    public void update(Student student) throws StudentException {
        initializeDB();
        String query = "UPDATE students SET name=?, age=?, average=? WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getAverage());
            statement.setString(4, student.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0)
                throw new StudentException("Student not found with ID: " + student.getId(),ID_NOT_FOUND);

        } catch (SQLException e) {
            throw new StudentException(e.getMessage(), e, JDBC_SQL_EXCEPTION);
        }
    }

}
