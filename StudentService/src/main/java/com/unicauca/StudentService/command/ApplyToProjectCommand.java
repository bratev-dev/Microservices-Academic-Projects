package com.unicauca.StudentService.command;

import com.unicauca.StudentService.Entities.Postulation;
import com.unicauca.StudentService.Services.StudentService;

/**
 *
 * @author jpala
 */
public class ApplyToProjectCommand implements Command{
    
    private final StudentService studentService;
    private final int studentId;
    private final int projectId;

    public ApplyToProjectCommand(StudentService studentService, int studentId, int projectId) {
        this.studentService = studentService;
        this.studentId = studentId;
        this.projectId = projectId;
    }

    @Override
    public void execute() {
        Postulation postulation = studentService.applyToProject(studentId, projectId);
        System.out.println("Postulación realizada: " + postulation);

        // Llamada a la notificación REST (puedes cambiar a RabbitMQ más adelante)
        studentService.notifyCoordinatorPostulation((long) studentId, (long) projectId);
    }

}
