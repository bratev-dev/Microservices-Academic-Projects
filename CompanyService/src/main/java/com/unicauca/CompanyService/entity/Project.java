package com.unicauca.CompanyService.entity;

import com.unicauca.CompanyService.observer.IObserver;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

    @Id
    private int id;
    private String name;
    private String summary;
    private String goals;
    private String description;
    private String maxTimeInMonths;
    private String budget;
    private String date;
    private String state;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private String comments;
    private int calificacion;
    private String request;
    //private Student[] students;

    @Transient
    private List<IObserver> observers = new ArrayList<>();

    // Métodos para manejar observadores (Observer Pattern)
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(this, message);
        }
    }
}
