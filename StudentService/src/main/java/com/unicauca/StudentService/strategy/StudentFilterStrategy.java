package com.unicauca.StudentService.strategy;

import com.unicauca.StudentService.Entities.Student;
import java.util.List;

/**
 *
 * @author jpala
 */
public interface StudentFilterStrategy {
    List<Student> filter (List<Student> students);
}
