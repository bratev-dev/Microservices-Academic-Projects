
package com.unicauca.StudentService.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 *
 * @author jpala
 */

@Data
@Entity
@Table(name = "postulaciones")
public class Postulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    private int projectId;
    private String state;
    private LocalDateTime applicationDate;
}
