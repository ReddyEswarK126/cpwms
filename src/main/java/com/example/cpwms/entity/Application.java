package com.example.cpwms.entity;

import com.example.cpwms.enums.ApplicationState;
import jakarta.persistence.*;

@Entity
@Table(
        name = "applications",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"student_id", "job_role_id"})
        }
)

public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private JobRole jobRole;

    @Enumerated(EnumType.STRING)
    private ApplicationState state;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public JobRole getJobRole() { return jobRole; }
    public void setJobRole(JobRole jobRole) { this.jobRole = jobRole; }

    public ApplicationState getState() { return state; }
    public void setState(ApplicationState state) { this.state = state; }
}
