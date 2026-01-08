package com.example.cpwms.service;

import com.example.cpwms.enums.ApplicationState;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Map;

@Component
public class TransitionValidator {

    private static final Map<ApplicationState, EnumSet<ApplicationState>> ALLOWED_TRANSITIONS =
            Map.of(
                    ApplicationState.APPLIED,
                    EnumSet.of(ApplicationState.ELIGIBLE, ApplicationState.INELIGIBLE),

                    ApplicationState.ELIGIBLE,
                    EnumSet.of(ApplicationState.SHORTLISTED),

                    ApplicationState.SHORTLISTED,
                    EnumSet.of(ApplicationState.INTERVIEWED, ApplicationState.REJECTED),

                    ApplicationState.INTERVIEWED,
                    EnumSet.of(ApplicationState.OFFERED, ApplicationState.REJECTED)
            );

    public boolean isValid(ApplicationState from, ApplicationState to) {
        return ALLOWED_TRANSITIONS
                .getOrDefault(from, EnumSet.noneOf(ApplicationState.class))
                .contains(to);
    }
}
