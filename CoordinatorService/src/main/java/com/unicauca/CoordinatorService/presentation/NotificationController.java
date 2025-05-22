package com.unicauca.CoordinatorService.controller;

import com.unicauca.CoordinatorService.infra.dto.PostulationNotificationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordinator")
@CrossOrigin
public class NotificationController {

    @PostMapping("/notify-postulation")
    public ResponseEntity<String> recibirNotificacion(@RequestBody PostulationNotificationDTO dto) {
        System.out.println("📢 Estudiante " + dto.getStudentId() + " se postuló al proyecto " + dto.getProjectId());
        return ResponseEntity.ok("Notificación recibida correctamente");
    }
}
