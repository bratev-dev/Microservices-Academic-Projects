
package com.unicauca.StudentService.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 *
 * @author jpala
 */

@Getter
@Setter
@Entity
@Table(name = "postulaciones")
public class Postulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    private int projectId;
    private String state;
    private LocalDateTime applicationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Postulation)) return false;
        Postulation that = (Postulation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

