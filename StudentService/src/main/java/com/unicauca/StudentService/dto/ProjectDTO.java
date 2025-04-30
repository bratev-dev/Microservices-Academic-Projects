
package com.unicauca.StudentService.dto;

import lombok.Data;

/**
 *
 * @author jpala
 */
@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private String state;
    private String companyName;
}
