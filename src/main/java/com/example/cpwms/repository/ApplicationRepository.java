package com.example.cpwms.repository;

import com.example.cpwms.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByStudentIdAndJobRoleId(Long studentId, Long jobRoleId);

}
