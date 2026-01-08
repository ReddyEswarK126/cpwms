package com.example.cpwms.repository;

import com.example.cpwms.entity.EligibilityRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EligibilityRuleRepository extends JpaRepository<EligibilityRule, Long> {

    List<EligibilityRule> findByJobRoleId(Long jobRoleId);

}
