package com.example.cpwms.entity;

import com.example.cpwms.enums.StudentStatus;
import jakarta.persistence.*;

@Entity
@Table(
        name = "students",
        indexes = {
                @Index(name = "idx_student_batch", columnList = "batch_year")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String branch;
    private Double cgpa;
    private Integer backlogs;
    private Integer batchYear;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public Integer getBacklogs() { return backlogs; }
    public void setBacklogs(Integer backlogs) { this.backlogs = backlogs; }

    public Integer getBatchYear() { return batchYear; }
    public void setBatchYear(Integer batchYear) { this.batchYear = batchYear; }

    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }
}
