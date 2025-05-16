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
        Student s1 = new Student("Jhonatan", "9", "Java, Spring Boot, Git", "jhonatan@email.com", "1234");
        Student s2 = new Student("Natalia", "10", "Node.js, Express, MongoDB", "natalia@email.com", "1234");
        Student s3 = new Student("Juan", "8", "Python, Django, PostgreSQL", "juan@email.com", "1234");
    }
}
