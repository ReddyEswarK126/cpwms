package com.example.cpwms.entity;

import com.example.cpwms.enums.Operator;
import com.example.cpwms.enums.RuleType;
import jakarta.persistence.*;

@Entity
@Table(name = "eligibility_rules")
public class EligibilityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RuleType ruleType;

    @Enumerated(EnumType.STRING)
    private Operator operator;

    private String value;

    @ManyToOne
    private JobRole jobRole;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RuleType getRuleType() { return ruleType; }
    public void setRuleType(RuleType ruleType) { this.ruleType = ruleType; }

    public Operator getOperator() { return operator; }
    public void setOperator(Operator operator) { this.operator = operator; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public JobRole getJobRole() { return jobRole; }
    public void setJobRole(JobRole jobRole) { this.jobRole = jobRole; }
}
