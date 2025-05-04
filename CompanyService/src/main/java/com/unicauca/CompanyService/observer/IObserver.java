package com.unicauca.CompanyService.observer;

import com.unicauca.CompanyService.entity.Project;

public interface IObserver {
    public void update(Project project, String message);
    public void removeObserver(IObserver observer);
    public void notifyObservers(String message);

    //void removeObserver(com.unicauca.CompanyService.observer.IObserver observer);
}

