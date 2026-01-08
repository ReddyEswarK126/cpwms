package com.example.cpwms.service;

import com.example.cpwms.entity.EligibilityRule;
import com.example.cpwms.entity.Student;
import com.example.cpwms.enums.Operator;
import com.example.cpwms.enums.RuleType;
import org.springframework.stereotype.Component;

@Component
public class EligibilityEngine {

    public boolean evaluate(Student student, EligibilityRule rule) {

        RuleType type = rule.getRuleType();
        Operator operator = rule.getOperator();
        String value = rule.getValue();

        switch (type) {
            case CGPA:
                return compare(student.getCgpa(), Double.parseDouble(value), operator);

            case BACKLOGS:
                return compare(student.getBacklogs(), Integer.parseInt(value), operator);

            case BATCH:
                return compare(student.getBatchYear(), Integer.parseInt(value), operator);

            case BRANCH:
                return student.getBranch().equalsIgnoreCase(value);

            default:
                return false;
        }
    }

    private boolean compare(double actual, double expected, Operator operator) {
        return switch (operator) {
            case GREATER_THAN -> actual >= expected;
            case LESS_THAN -> actual <= expected;
            case EQUAL -> actual == expected;
        };
    }

    private boolean compare(int actual, int expected, Operator operator) {
        return switch (operator) {
            case GREATER_THAN -> actual >= expected;
            case LESS_THAN -> actual <= expected;
            case EQUAL -> actual == expected;
        };
    }
}
