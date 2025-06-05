package com.unicauca.CoordinatorService.presentation;

import com.unicauca.CoordinatorService.presentation.dto.PostulationNotificationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordinator")
@CrossOrigin
public class NotificationController {

    @PostMapping("/notify-postulation")
    @PreAuthorize("hasRole('coordinator')")
    public ResponseEntity<String> recibirNotificacion(@RequestBody PostulationNotificationDTO dto) {
        System.out.println("📢 Estudiante " + dto.getStudentId() + " se postuló al proyecto " + dto.getProjectId());
        return ResponseEntity.ok("Notificación recibida correctamente");
    }
}
