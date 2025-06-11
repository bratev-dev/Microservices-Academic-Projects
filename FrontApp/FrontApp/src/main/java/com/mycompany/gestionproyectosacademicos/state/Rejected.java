package com.mycompany.gestionproyectosacademicos.state;

import com.mycompany.gestionproyectosacademicos.entities.Project;

/**
 *
 * @author Jhonatan
 */
public class Rejected implements ProjectState {

    public void manageState(Project project) {
        project.setStatus(getStateName());
    }

    public String getStateName() {
        return "Rechazado";
    }
}
