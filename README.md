# Student API

This is a Spring Boot project named "student-api" that provides an API for managing student records. It uses OAuth2 security for authentication and authorization.

## Project Information
- Name: student-api
- Description: Demo project for Spring Boot
- Java Version: 1.8
- Framework: Spring Boot 2.7.0

## API Information

## Request mappings
The main controller in this project is `StudentController`, which handles HTTP requests related to student records. The following endpoints are available:

- `GET /api/v1/students`: Retrieves all students from the database.
- `GET /api/v1/students/{id}`: Retrieves a student by ID from the database.
- `GET /api/v1/students/search`: Searches for students based on name, average, and comparison operator.
- `POST /api/v1/students/`: creates new student.
- `DELETE /api/v1/students/search`: Deletes student using id.
- `PUT /api/v1/students/`: Updates student.


### Get All Students
Get all students with the specified implementation type.

**URL:** `/api/v1/students`
**Method:** GET
**Parameters:**
- `implType` (required): The implementation type of the students (Enum: ImplType).

**Response:**
- Status Code: 200 OK
- Body: List of students in JSON format.

### Get Student by ID
Get a student by ID with the specified implementation type.

**URL:** `/api/v1/students/{id}`
**Method:** GET
**Parameters:**
- `id` (required): The ID of the student.
- `implType` (required): The implementation type of the student (Enum: ImplType).

**Response:**
- Status Code: 200 OK
- Body: Student details in JSON format.

### Search Students
Search for students with the specified criteria and implementation type.

**URL:** `/api/v1/students/search`
**Method:** GET
**Parameters:**
- `name` (optional): The name of the student.
- `average` (optional): The average of the student.
- `operator` (optional): The comparison operator for average (EQ, GT, LT).
- `implType` (required): The implementation type of the students (Enum: ImplType).

**Response:**
- Status Code: 200 OK
- Body: List of students matching the criteria in JSON format.

### Add Student
Add a new student with the specified implementation type.

**URL:** `/api/v1/students`
**Method:** POST
**Parameters:**
- `implType` (required): The implementation type of the student (Enum: ImplType).
- Request Body: Student details in JSON format.

**Response:**
- Status Code: 201 Created
- Body: Student details in JSON format.

### Update Student
Update an existing student with the specified implementation type.

**URL:** `/api/v1/students/{id}`
**Method:** PUT
**Parameters:**
- `id` (required): The ID of the student.
- `implType` (required): The implementation type of the student (Enum: ImplType).
- Request Body: Updated student details in JSON format.

**Response:**
- Status Code: 200 OK
- Body: Updated student details in JSON format.

### Delete Student
Delete an existing student with the specified implementation type.

**URL:** `/api/v1/students/{id}`
**Method:** DELETE
**Parameters:**
- `id` (required): The ID of the student.
- `implType` (required): The implementation type of the student (Enum: ImplType).

**Response:**
- Status Code: 200 OK

## OAuth2
This project uses OAuth2 for authentication and authorization using github.