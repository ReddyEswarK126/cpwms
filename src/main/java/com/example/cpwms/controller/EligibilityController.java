package com.example.cpwms.controller;

import com.example.cpwms.entity.JobRole;
import com.example.cpwms.entity.Student;
import com.example.cpwms.service.EligibilityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(EligibilityService eligibilityService) {
        this.eligibilityService = eligibilityService;
    }

    @PostMapping("/check")
    public boolean checkEligibility(@RequestBody EligibilityRequest request) {
        return eligibilityService.isEligible(
                request.student(),
                request.jobRole()
        );
    }

    public record EligibilityRequest(Student student, JobRole jobRole) {}
}
