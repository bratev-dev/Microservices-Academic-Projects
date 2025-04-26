
package com.unicauca.StudentService.Services;

import com.unicauca.StudentService.Entities.Student;
import com.unicauca.StudentService.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
/**
 *
 * @author jpala
 */

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
    
    public List<Student> findStudentsBySemester(String semester) {
        return studentRepository.findBySemester(semester);
    }
    
    public List<Student> findStudentsBySkill(String skill) {
        return studentRepository.findBySkillsContaining(skill);
    }
    
}
