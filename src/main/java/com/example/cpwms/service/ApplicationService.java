package com.example.cpwms.service;

import com.example.cpwms.entity.Application;
import com.example.cpwms.entity.Student;
import com.example.cpwms.entity.JobRole;
import com.example.cpwms.enums.ApplicationState;
import com.example.cpwms.repository.ApplicationRepository;
import com.example.cpwms.repository.StudentRepository;
import com.example.cpwms.repository.JobRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRoleRepository jobRoleRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              JobRoleRepository jobRoleRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRoleRepository = jobRoleRepository;
    }

    public Application apply(Long studentId, Long jobRoleId) {

        if (applicationRepository.existsByStudentIdAndJobRoleId(studentId, jobRoleId)) {
            throw new RuntimeException("Student already applied for this job");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        JobRole jobRole = jobRoleRepository.findById(jobRoleId)
                .orElseThrow(() -> new RuntimeException("Job role not found"));

        Application application = new Application();
        application.setStudent(student);
        application.setJobRole(jobRole);
        application.setState(ApplicationState.APPLIED);

        return applicationRepository.save(application);
    }
}
