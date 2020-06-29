package com.schooldevops.springcloud.kube.resources;

import com.google.api.client.util.Value;
import com.schooldevops.springcloud.kube.configs.PropertiesConfig;
import com.schooldevops.springcloud.kube.models.Education;
import com.schooldevops.springcloud.kube.models.Student;
import com.schooldevops.springcloud.kube.services.EducationService;
import com.schooldevops.springcloud.kube.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/educations")
public class EducationController {

    @Autowired
    private PropertiesConfig propertiesConfig;

    private final EducationService educationService;
    private final StudentService studentService;

    public EducationController(EducationService educationService, StudentService studentService) {
        this.educationService = educationService;
        this.studentService = studentService;
    }

    @GetMapping("/message")
    public String getMessage() {
        return propertiesConfig.getMessage();
    }

    @GetMapping("/servers")
    public List<String> getServerList() {
        log.info("---------- getServerList");
        return educationService.getServerList();
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        log.info("---------- getAllStudents");
        return educationService.getAllStudents();
    }

    @GetMapping("/education/{majorSubject}")
    public Education getEducationByMajorSubject(@PathVariable String majorSubject) {
        log.info("---------- getEducationByMajorSubject");
        return educationService.getEducationByMajorSubject(majorSubject);
    }

    @GetMapping("/feign/students")
    public List<Student> getAllStudentsByFeign() {
        log.info("---------- fetchAll Student By Feign");
        return studentService.getAllStudents();
    }


}
