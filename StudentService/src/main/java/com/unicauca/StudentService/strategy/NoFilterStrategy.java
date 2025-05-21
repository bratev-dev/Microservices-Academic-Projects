package com.unicauca.StudentService.strategy;

import com.unicauca.StudentService.Entities.Student;
import java.util.List;

/**
 *
 * @author jpala
 */

//Retorna todos los estudiantes ya que no se aplica ningun filtro.
public class NoFilterStrategy implements StudentFilterStrategy{

    @Override
    public List<Student> filter(List<Student> students) {
        return students;
    }
    
}
