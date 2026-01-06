package com.example.cpwms.service;

import com.example.cpwms.entity.Application;
import com.example.cpwms.enums.ApplicationState;
import com.example.cpwms.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationWorkflowService {

    private final ApplicationRepository applicationRepository;
    private final TransitionValidator transitionValidator;
    private final AuditService auditService;

    public ApplicationWorkflowService(ApplicationRepository applicationRepository,
                                      TransitionValidator transitionValidator,
                                      AuditService auditService) {
        this.applicationRepository = applicationRepository;
        this.transitionValidator = transitionValidator;
        this.auditService = auditService;
    }

    public Application changeState(Long applicationId, ApplicationState newState) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        ApplicationState currentState = application.getState();

        if (!transitionValidator.isValid(currentState, newState)) {
            throw new IllegalStateException(
                    "Invalid transition from " + currentState + " to " + newState
            );
        }

        application.setState(newState);
        Application saved = applicationRepository.save(application);

        auditService.log(
                "APPLICATION",
                applicationId,
                "STATE_CHANGED: " + currentState + " → " + newState
        );

        return saved;
    }
}
