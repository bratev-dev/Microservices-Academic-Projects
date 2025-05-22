
package com.unicauca.StudentService.Services;

import com.unicauca.StudentService.Entities.Postulation;
import com.unicauca.StudentService.Entities.Student;
import com.unicauca.StudentService.Repository.PostulationRepository;
import com.unicauca.StudentService.Repository.StudentRepository;
import com.unicauca.StudentService.strategy.StudentFilterStrategy;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
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
    private final PostulationRepository postulationRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository, PostulationRepository postulationRepository) {
        this.studentRepository = studentRepository;
        this.postulationRepository = postulationRepository;
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
    
    public Postulation applyToProject(int studentId, int projectId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + studentId);
        }

        Student student = studentOpt.get();

        Postulation postulation = new Postulation();
        postulation.setStudent(student);
        postulation.setProjectId(projectId);
        postulation.setState("Pendiente");
        postulation.setApplicationDate(LocalDateTime.now());

        student.getPostulations().add(postulation); // Asegura la relación

        Postulation saved = postulationRepository.save(postulation);
        System.out.println("Postulación guardada: " + saved);
        return saved;
    }
    
    public void notifyCoordinatorPostulation(Long studentId, Long projectId) {
        try {
            URL url = new URL("http://localhost:8082/api/coordinator/notify-postulation"); // puerto del Coordinador
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = String.format("{\"studentId\":%d,\"projectId\":%d}", studentId, projectId);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.err.println("Error al notificar al coordinador: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Servicio para la implemetacion del patron Strategy
    public List<Student> getFilteredStudents(StudentFilterStrategy strategy) {
        List<Student> allStudents = studentRepository.findAll();
        return strategy.filter(allStudents);
    }

    
}
