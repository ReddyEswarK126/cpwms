package com.example.cpwms.service;

import com.example.cpwms.entity.EligibilityRule;
import com.example.cpwms.entity.Student;
import com.example.cpwms.enums.Operator;
import com.example.cpwms.enums.RuleType;
import org.springframework.stereotype.Component;

@Component
public class EligibilityEvaluator {

    public boolean evaluate(Student student, EligibilityRule rule) {

        RuleType type = rule.getRuleType();
        Operator operator = rule.getOperator();
        String ruleValue = rule.getValue();

        switch (type) {

            case CGPA:
                return compare(
                        student.getCgpa(),
                        Double.parseDouble(ruleValue),
                        operator
                );

            case BACKLOGS:
                return compare(
                        student.getBacklogs(),
                        Integer.parseInt(ruleValue),
                        operator
                );

            case BATCH:
                return compare(
                        student.getBatchYear(),
                        Integer.parseInt(ruleValue),
                        operator
                );

            case BRANCH:
                return compare(
                        student.getBranch(),
                        ruleValue,
                        operator
                );

            default:
                throw new IllegalArgumentException("Unknown rule type");
        }
    }

    private boolean compare(Double actual, Double expected, Operator op) {
        return switch (op) {
            case GREATER_THAN -> actual > expected;
            case LESS_THAN -> actual < expected;
            case EQUAL -> actual.equals(expected);
        };
    }

    private boolean compare(Integer actual, Integer expected, Operator op) {
        return switch (op) {
            case GREATER_THAN -> actual > expected;
            case LESS_THAN -> actual < expected;
            case EQUAL -> actual.equals(expected);
        };
    }

    private boolean compare(String actual, String expected, Operator op) {
        if (op != Operator.EQUAL) {
            throw new IllegalArgumentException("Only EQUAL allowed for strings");
        }
        return actual.equalsIgnoreCase(expected);
    }
}
