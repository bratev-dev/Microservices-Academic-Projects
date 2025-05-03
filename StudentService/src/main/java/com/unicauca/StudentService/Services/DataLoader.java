package com.unicauca.StudentService.Services;

import com.unicauca.StudentService.Entities.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final StudentService studentService;

    public DataLoader(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostConstruct
    public void loadData() {
            studentService.saveStudent(new Student("Jhonatan", "9", "Java, Spring Boot, Git"));
            studentService.saveStudent(new Student("Natalia", "10", "Node.js, Express, MongoDB"));
            studentService.saveStudent(new Student("Juan", "8", "Python, Django, PostgreSQL"));
    }
}
