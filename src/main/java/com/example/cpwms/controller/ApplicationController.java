package com.example.cpwms.controller;

import com.example.cpwms.entity.Application;
import com.example.cpwms.enums.ApplicationState;
import com.example.cpwms.repository.ApplicationRepository;
import com.example.cpwms.service.ApplicationWorkflowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final ApplicationWorkflowService workflowService;

    public ApplicationController(ApplicationRepository applicationRepository,
                                 ApplicationWorkflowService workflowService) {
        this.applicationRepository = applicationRepository;
        this.workflowService = workflowService;
    }

    // 1️⃣ Create application (APPLIED)
    @PostMapping
    public Application apply(@RequestBody Application application) {
        application.setState(ApplicationState.APPLIED);
        return applicationRepository.save(application);
    }

    // 2️⃣ Change application state
    @PutMapping("/{id}/state")
    public Application changeState(@PathVariable Long id,
                                   @RequestParam ApplicationState newState) {
        return workflowService.changeState(id, newState);
    }
}
