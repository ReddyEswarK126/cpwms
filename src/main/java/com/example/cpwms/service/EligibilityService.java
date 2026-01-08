package com.example.cpwms.service;

import com.example.cpwms.entity.EligibilityRule;
import com.example.cpwms.entity.JobRole;
import com.example.cpwms.entity.Student;
import com.example.cpwms.repository.EligibilityRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityService {

    private final EligibilityRuleRepository ruleRepository;
    private final EligibilityEvaluator evaluator;

    public EligibilityService(EligibilityRuleRepository ruleRepository,
                              EligibilityEvaluator evaluator) {
        this.ruleRepository = ruleRepository;
        this.evaluator = evaluator;
    }

    public boolean isEligible(Student student, JobRole jobRole) {

        List<EligibilityRule> rules =
                ruleRepository.findAll()
                        .stream()
                        .filter(r -> r.getJobRole().getId().equals(jobRole.getId()))
                        .toList();

        for (EligibilityRule rule : rules) {
            boolean passed = evaluator.evaluate(student, rule);
            if (!passed) {
                return false; // FAIL FAST
            }
        }

        return true; // ALL RULES PASSED
    }
}
