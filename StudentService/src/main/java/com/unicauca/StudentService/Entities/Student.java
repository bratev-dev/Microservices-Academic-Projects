package com.unicauca.StudentService.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String semester;
    
    @Column(nullable = false)
    private String skills;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Postulation> postulations = new HashSet<>();
    
    public Student(int id, String name, String semester, String skills) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.skills = skills;
    }
}
