package com.example.cpwms.controller;

import com.example.cpwms.entity.Application;
import com.example.cpwms.enums.ApplicationState;
import com.example.cpwms.service.ApplicationService;
import com.example.cpwms.service.ApplicationWorkflowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationWorkflowService workflowService;

    public ApplicationController(ApplicationService applicationService,
                                 ApplicationWorkflowService workflowService) {
        this.applicationService = applicationService;
        this.workflowService = workflowService;
    }

    // ✅ Create application (APPLIED)
    @PostMapping
    public Application apply(@RequestParam Long studentId,
                             @RequestParam Long jobRoleId) {
        return applicationService.apply(studentId, jobRoleId);
    }

    // ✅ Change application state
    @PutMapping("/{id}/state")
    public Application changeState(@PathVariable Long id,
                                   @RequestParam ApplicationState newState) {
        return workflowService.changeState(id, newState);
    }
}
