
package com.unicauca.StudentService.Controller;

import com.unicauca.StudentService.dto.StudentDTO;
import com.unicauca.StudentService.Entities.Student;
import com.unicauca.StudentService.Services.StudentService;
import com.unicauca.StudentService.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jpala
 */

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private final StudentService studentService;
    private final RestTemplate restTemplate;
    
    @Autowired
    public StudentController(StudentService studentService, RestTemplate restTemplate){
        this.studentService = studentService;
        this.restTemplate = restTemplate;
    }
    
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentDTO> studentDTOs = students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }

    //@GetMapping("/api/students/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(convertToDTO(savedStudent), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        if (!studentService.getStudentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Student student = convertToEntity(studentDTO);
        student.setId(id);
        Student updatedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(convertToDTO(updatedStudent));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        if (!studentService.getStudentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/semester/{semester}")
    public ResponseEntity<List<StudentDTO>> getStudentsBySemester(@PathVariable String semester) {
        List<Student> students = studentService.findStudentsBySemester(semester);
        List<StudentDTO> studentDTOs = students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }
    
    @GetMapping("/skills")
    public ResponseEntity<List<StudentDTO>> getStudentsBySkill(@RequestParam String skill) {
        List<Student> students = studentService.findStudentsBySkill(skill);
        List<StudentDTO> studentDTOs = students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }
    
     // Nuevo endpoint para obtener proyectos por companyId
    @GetMapping("/projects/{companyId}")
    public ResponseEntity<String> getProjectsByCompanyId(@PathVariable Long companyId) {
        // Llamada al microservicio de proyectos para obtener los proyectos de una empresa
        String projectServiceUrl = "http://project-service/api/projects/company/" + companyId;
        String projects = restTemplate.getForObject(projectServiceUrl, String.class);

        if (projects == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No projects found for company id: " + companyId);
        }
        
        return ResponseEntity.ok(projects);
    }
    
    // Métodos de conversión entre entidad y DTO
    private StudentDTO convertToDTO(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSemester(student.getSemester());
        dto.setSkills(student.getSkills());
        return dto;
    }

    private Student convertToEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setSemester(dto.getSemester());
        student.setSkills(dto.getSkills());
        return student;
    }    
    
    //Implementacion para el Patron Command
    @PostMapping("/{studentId}/postulate/{projectId}")
    public ResponseEntity<String> postulateToProject(@PathVariable int studentId, @PathVariable int projectId) {
        Command command = new ApplyToProjectCommand(studentService, studentId, projectId);
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(command);
        invoker.executeCommand();
        return ResponseEntity.ok("Postulación ejecutada exitosamente.");
    }
    
}
