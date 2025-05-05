
package com.unicauca.StudentService.Repository;

import com.unicauca.StudentService.Entities.Postulation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jpala
 */
public interface PostulationRepository extends JpaRepository<Postulation, Integer> {
    
}
