package com.unicauca.StudentService.strategy;

import com.unicauca.StudentService.Entities.Student;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jpala
 */

//Filtra los estudiantes por semestres mediante el atributo semestre.
public class SemesterFilterStrategy implements StudentFilterStrategy {
    private final String semester;
    
    public SemesterFilterStrategy(String semester) {
        this.semester = semester;
    }
    
    @Override
    public List<Student> filter(List<Student> students) {
        return students.stream()
                .filter(student -> student.getSemester().equals(semester))
                .collect(Collectors.toList());
    }
    
}