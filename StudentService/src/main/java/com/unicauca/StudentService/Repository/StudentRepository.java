
package com.unicauca.StudentService.Repository;

import com.unicauca.StudentService.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jpala
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findBySemester(String semester);
    List<Student> findBySkillsContaining(String skill);
    Student findByName(String name);

    public Optional<Student> findById(int id);

    public void deleteById(int id);
    Optional<Student> findByEmailAndPassword(String email, String password);

}