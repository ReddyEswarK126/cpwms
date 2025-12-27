package com.example.cpwms.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "job_roles",
        indexes = {
                @Index(name = "idx_job_company", columnList = "company_id")
        }
)

public class JobRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double minCtc;
    private Double maxCtc;

    @ManyToOne
    private Company company;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getMinCtc() { return minCtc; }
    public void setMinCtc(Double minCtc) { this.minCtc = minCtc; }

    public Double getMaxCtc() { return maxCtc; }
    public void setMaxCtc(Double maxCtc) { this.maxCtc = maxCtc; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
