package de.arag.functinal.programming.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "_employee")
public class Employee {
    @Id
    private String id;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String jobTitle;
    @Column(nullable = false)
    private Date createdAt;
    private Date updatedAt;
    private Boolean isActive;
    @Column(nullable = false)
    private String departmentName;
}
